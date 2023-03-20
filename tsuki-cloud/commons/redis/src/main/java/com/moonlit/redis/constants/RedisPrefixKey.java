package com.moonlit.redis.constants;

/**
 * Redis缓存前缀
 *
 * @author Joshua
 * @version 1.0
 * @date 20/3/2023 18:03
 * @email by.Moonlit@hotmail.com
 */
public interface RedisPrefixKey {

    /**
     * 设置缓存过期时间
     *
     * @return 过期时间
     */
    Long setExpireSeconds();

    /**
     * key前缀
     *
     * @return 键前缀
     */
    String getPrefix();

}
