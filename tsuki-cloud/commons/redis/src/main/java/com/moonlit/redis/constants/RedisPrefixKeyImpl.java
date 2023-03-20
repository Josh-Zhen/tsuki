package com.moonlit.redis.constants;

/**
 * Redis缓存前缀抽象类
 *
 * @author Joshua
 * @version 1.0
 * @date 20/3/2023 18:05
 * @email by.Moonlit@hotmail.com
 */
public abstract class RedisPrefixKeyImpl implements RedisPrefixKey {

    /**
     * 过期时间
     */
    private Long expireSeconds;

    /**
     * Key前缀
     */
    public String prefix;


    @Override
    public Long setExpireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        return getClass().getSimpleName();
    }

}
