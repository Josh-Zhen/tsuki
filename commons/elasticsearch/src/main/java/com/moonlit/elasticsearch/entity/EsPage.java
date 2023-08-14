package com.moonlit.elasticsearch.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * es分页实体
 *
 * @author Joshua
 * @version 1.0
 * @date 12/8/2023 19:01
 */
@Data
@NoArgsConstructor
public class EsPage<T> {

    /**
     * 数据
     */
    private List<T> rows;

    /**
     * 总页数
     */
    private Integer totalPage = 0;

    /**
     * 总条数
     */
    private Integer totalRows = 0;

    /**
     * 当前页码
     */
    private Integer pageNo;

    /**
     * 每页条数
     */
    private Integer pageSize;

    public EsPage(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public EsPage(List<T> rows, Integer pageNo, Integer pageSize) {
        this.rows = rows;
        this.totalPage = (totalRows - 1) / pageSize + 1;
        this.totalRows = rows.size();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

}
