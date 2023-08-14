package com.moonlit.elasticsearch.utils;

import cn.hutool.core.util.ObjectUtil;
import com.moonlit.common.constant.CharacterConstant;
import com.moonlit.elasticsearch.annotation.EsDataIndex;
import com.moonlit.elasticsearch.annotation.EsId;
import com.moonlit.elasticsearch.constant.ElasticsearchCodeEnum;

import java.lang.reflect.Field;

/**
 * ES工具类
 *
 * @author Joshua
 * @version 1.0
 * @date 6/8/2023 1:16
 */
public class EsTools {

    /**
     * 获取索引名称
     *
     * @param clazz 类
     * @return 名称
     */
    public static String getIndexName(Class<?> clazz) {
        EsDataIndex index = clazz.getAnnotation(EsDataIndex.class);
        if (ObjectUtil.isEmpty(index)) {
            throw new IllegalArgumentException(ElasticsearchCodeEnum.ANNOTATION_IS_NOT_CONFIGURED.getMessage());
        }
        return index.indexName();
    }

    /**
     * 获取对象id
     *
     * @param obj 类
     * @return id
     */
    public static String getEsId(Object obj) {
        String esId = null;
        // 遍历注解
        for (Field f : obj.getClass().getDeclaredFields()) {
            // 当存在EsId的注解时则获取该注解所对应的内容
            if (ObjectUtil.isNotEmpty(f.getAnnotation(EsId.class))) {
                // 给予属性的范围权限
                f.setAccessible(true);
                try {
                    esId = String.valueOf(f.get(obj));
                    break;
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException(ElasticsearchCodeEnum.ANNOTATION_IS_NOT_CONFIGURED.getMessage());
                }
            }
        }
        // 无赋值时抛异常
        if (CharacterConstant.IN_VAIN.equals(esId)) {
            throw new IllegalArgumentException(ElasticsearchCodeEnum.ANNOTATION_IS_NOT_CONFIGURED.getMessage());
        }
        return esId;
    }

}