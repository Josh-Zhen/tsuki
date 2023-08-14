package com.moonlit.elasticsearch;

import cn.hutool.core.util.ObjectUtil;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch._types.FieldSort;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.util.ObjectBuilder;
import com.moonlit.common.exception.BusinessException;
import com.moonlit.elasticsearch.constant.ElasticsearchCodeEnum;
import com.moonlit.elasticsearch.entity.EsPage;
import com.moonlit.elasticsearch.entity.ParamsBO;
import com.moonlit.elasticsearch.entity.SearchParameterBO;
import com.moonlit.elasticsearch.utils.EsTools;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Es业务类
 *
 * @author Joshua
 * @version 1.0
 * @date 4/8/2023 21:57
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ElasticsearchService<T> {

    /**
     * 同步
     */
    private final ElasticsearchClient client;

    /**
     * 创建索引
     *
     * @param indexName 索引名称
     */
    public void createIndex(String indexName) {
        if (ObjectUtil.isEmpty(indexName)) {
            throw new IllegalArgumentException("'index' cannot be empty");
        }
        try {
            client.indices().create(c -> c.index(indexName));
        } catch (IOException e) {
            log.error("----ES create 'index' fail >>>>indexName: {}", indexName);
            throw new BusinessException(e);
        }
    }

    /**
     * 删除索引
     *
     * @param indexName 索引名称
     */
    public void deleteIndex(String indexName) {
        try {
            client.indices().delete(c -> c.index(indexName));
        } catch (IOException e) {
            log.error("----ES delete 'index' fail >>>>indexName: {}", indexName);
            throw new BusinessException(e);
        }
    }

    /**
     * 判断索引是否存在
     *
     * @param indexName 索引名称
     */
    public Boolean existsIndex(String indexName) {
        try {
            return client.indices().exists(c -> c.index(indexName)).value();
        } catch (IOException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * 查询索引
     *
     * @param indexName 索引名称
     */
    public String queryIndex(String indexName) {
        try {
            return client.indices().get(c -> c.index(indexName)).toString();
        } catch (IOException | ElasticsearchException e) {
            throw new BusinessException(ElasticsearchCodeEnum.INDEX_DOES_NOT_EXIST);
        }
    }

    /**
     * 插入/更新文档
     *
     * @param t 实体类
     */
    public void insertOrUpdateDoc(T t) {
        String index = EsTools.getIndexName(t.getClass());
        String esId = EsTools.getEsId(t);
        try {
            client.index(i -> i.index(index).id(esId).document(t));
        } catch (IOException e) {
            log.error("---- ES insert Or Update Doc fail >>>>index: {}, esId: {} exception: {}", index, esId, e);
            throw new BusinessException(ElasticsearchCodeEnum.FAILED_ADD_DOCS);
        }
    }

    /**
     * 删除文档
     *
     * @param esId  esId
     * @param clazz 实体类
     */
    public void deleteDoc(String esId, Class<T> clazz) {
        String index = EsTools.getIndexName(clazz);
        try {
            client.delete(d -> d.index(index).id(esId));
        } catch (IOException e) {
            log.error("----ES delete 'Doc' fail >>>>esId: {}", esId);
            throw new BusinessException(e);
        }
    }

    /**
     * 根据EsId查询
     *
     * @param esId  esId
     * @param clazz 实体类
     * @return 内容
     */
    public T findByEsId(String esId, Class<T> clazz) {
        String index = EsTools.getIndexName(clazz);
        try {
            GetResponse<T> response = client.get(g -> g.index(index).id(esId), clazz);
            // id不存在
            if (!response.found()) {
                throw new IllegalArgumentException("index: '" + response.index() + "' id: '" + esId + "' does not exist");
            }
            // 获取内容
            return response.source();
        } catch (IOException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * 批量插入或更新
     *
     * @param batchList 数据集
     */
    public void batchInsertOrUpdate(List<T> batchList) {
        if (ObjectUtil.isEmpty(batchList) || batchList.size() < 1) {
            throw new NullPointerException();
        }
        // 获取索引
        String index = EsTools.getIndexName(batchList.get(0).getClass());

        List<BulkOperation> bulkOperations = new ArrayList<>();
        // 循环写入批量处理实体中
        batchList.forEach(object -> {
            String esId = EsTools.getEsId(object);
            bulkOperations.add(BulkOperation.of(o -> o.index(i -> i.id(esId).document(object))));
        });

        // 插入数据
        try {
            client.bulk(b -> b.index(index).operations(bulkOperations));
        } catch (IOException e) {
            log.error("---- ES insert Or Update Doc fail >>>>index: {}, exception: {}", index, e);
            throw new BusinessException(ElasticsearchCodeEnum.FAILED_ADD_DOCS_IN_BATCHES);
        }
    }

    /**
     * 分页条件查询(浅分页)
     *
     * @param bo    参数实体类
     * @param clazz 返回的实体类
     * @return 内容集
     */
    public EsPage<T> searchPage(SearchParameterBO bo, Class<T> clazz) {
        List<T> rows = new ArrayList<>();
        EsPage<T> page = new EsPage<>(1, 10);
        // 设置分页参数
        if (ObjectUtil.isNotEmpty(bo)) {
            page.setPageNo(bo.getPageNo());
            page.setPageSize(bo.getPageSize());
        }
        String indexName = EsTools.getIndexName(clazz);
        try {
            SearchResponse<T> response = client.search(s -> this.construct(s, bo, indexName, page), clazz);
            // 将数据写入集合中
            response.hits().hits().forEach(hit -> {
                rows.add(hit.source());
            });
            page.setRows(rows);
            page.setTotalPage((page.getTotalRows() - 1) / page.getPageSize() + 1);
            page.setTotalRows(rows.size());
        } catch (IOException e) {
            throw new BusinessException(e);
        }
        return page;
    }

    /* -------------------------------------------------- private -------------------------------------------------- */

    /**
     * 构建查询参数
     *
     * @param s  查询实体
     * @param bo 查询内容
     * @return 结果
     */
    private ObjectBuilder<SearchRequest> construct(SearchRequest.Builder s, SearchParameterBO bo, String indexName, EsPage<T> page) {
        // 设置查询索引
        s.index(indexName);
        // 有内容时查询
        if (bo.getParamsList().size() > 0) {
            s.query(q -> q.multiMatch(m -> this.queryParams(m, bo.getParamsList())));
        }

        // 设置分页参数(从第几条数据开始，每页多少条数据)
        if (ObjectUtil.isNotEmpty(page)) {
            s.from((page.getPageNo() - 1) * page.getPageSize())
                    .size(page.getPageSize());
        }
        // 排序
        if (bo.getIsSort()) {
            s.sort(f -> f.field(o -> this.sort(o, bo.getSort())));
        }
        return s;
    }

    /**
     * 查询参数构建器
     *
     * @param m          查询实体
     * @param paramsList 参数列表
     * @return 查询参数
     */
    private ObjectBuilder<MultiMatchQuery> queryParams(MultiMatchQuery.Builder m, List<ParamsBO> paramsList) {
        paramsList.forEach(params -> {
            m.fields(params.getFieldName())
                    .query(params.getValues())
                    .operator(Operator.And);
        });
        return m;
    }

    /**
     * 排序参数构建器
     *
     * @param o    排序实体
     * @param sort 自定义排序规则
     * @return 排序参数
     */
    private ObjectBuilder<FieldSort> sort(FieldSort.Builder o, HashMap<String, Boolean> sort) {
        sort.forEach((k, v) -> {
            o.field(k);
            if (v) {
                o.order(SortOrder.Desc);
            } else {
                o.order(SortOrder.Asc);
            }
        });
        return o;
    }

}
