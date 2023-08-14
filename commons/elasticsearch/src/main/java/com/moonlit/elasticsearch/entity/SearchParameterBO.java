package com.moonlit.elasticsearch.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

/**
 * 搜索参数BO
 *
 * @author Joshua
 * @version 1.0
 * @date 12/8/2023 21:15
 */
@Data
@NoArgsConstructor
public class SearchParameterBO {

    /**
     * 当前页码
     */
    private Integer pageNo;

    /**
     * 每页条数
     */
    private Integer pageSize;

    /**
     * 查询条件参数
     */
    private List<ParamsBO> paramsList;

    /**
     * 是否排序
     */
    private Boolean isSort;

    /**
     * 排序规则(t: DESC, f: ASE)
     */
    private HashMap<String, Boolean> sort;
}
