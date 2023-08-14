package com.moonlit.elasticsearch.annotation;

import java.lang.annotation.*;

/**
 * Es文档Id标识
 *
 * @author Joshua
 * @version 1.0
 * @date 6/8/2023 1:00
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EsId {
}
