package com.moonlit.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询参数
 *
 * @author Joshua
 * @version 1.0
 * @date 14/8/2023 14:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParamsBO {

    /**
     * 查询的字段名称
     */
    private String fieldName;

    /**
     * 查询模式（eq: 匹配, like: 模糊, gt: 大于, lt: 小于, gte: 大于或等于, lte: 小于或等于）
     * {@link com.moonlit.elasticsearch.constant.ElasticsearchConstant}
     */
    private String operator;

    /**
     * 类型
     */
    private String type;

    /**
     * 内容
     */
    private String values;

}
