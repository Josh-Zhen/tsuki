package com.moonlit.elasticsearch.annotation;

import java.lang.annotation.*;

/**
 * Es文档的索引名称
 *
 * @author Joshua
 * @version 1.0
 * @date 6/8/2023 1:03
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EsDataIndex {

    /**
     * @return 索引名称
     */
    String indexName() default "";

}
