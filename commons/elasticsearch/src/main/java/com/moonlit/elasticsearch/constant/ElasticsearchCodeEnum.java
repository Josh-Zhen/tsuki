package com.moonlit.elasticsearch.constant;

import com.moonlit.common.exception.base.AbstractBaseExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Es异常类
 *
 * @author Joshua
 * @version 1.0
 * @date 5/8/2023 20:30
 */
@Getter
@AllArgsConstructor
public enum ElasticsearchCodeEnum implements AbstractBaseExceptionEnum {

    /**
     * 异常枚举
     */
    ANNOTATION_IS_NOT_CONFIGURED(13001, "实体未配置 “@EsDataIndex” 注解"),
    ANNOTATED_PROPERTY_IS_EMPTY(13002, "实体内 ”@EsId“ 注解对应属性的值可能为空"),
    INDEX_DOES_NOT_EXIST(13003, "索引不存在"),
    FAILED_ADD_DOCS(13004, "添加doc失败"),
    FAILED_ADD_DOCS_IN_BATCHES(13005, "批量添加doc失败"),
    ;

    private Integer code;
    private String message;

}
