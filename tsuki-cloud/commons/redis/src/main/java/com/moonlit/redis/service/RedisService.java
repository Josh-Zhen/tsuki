package com.moonlit.redis.service;

import cn.hutool.core.util.ObjectUtil;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis業務類
 *
 * @author Joshua
 * @version 1.0
 * @date 20/3/2023 16:25
 * @email by.Moonlit@hotmail.com
 */
@Component
@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 切换数据库
     *
     * @param dataBaseNumber 数据库编号
     */
    public void changeDataBase(@NotNull Integer dataBaseNumber) {
        LettuceConnectionFactory factory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
        if (ObjectUtil.isNotEmpty(factory) && dataBaseNumber != factory.getDatabase()) {
            factory.setDatabase(dataBaseNumber);
            this.redisTemplate.setConnectionFactory(factory);
            factory.resetConnection();
        }
    }

    /**
     * 设置有效时间
     *
     * @param key     键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public Boolean expire(final String key, final Long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key     键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public Boolean expire(final String key, final Long timeout, final TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /*-------------------------------------- Set --------------------------------------*/

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   键
     * @param value 值
     */
    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key      键
     * @param value    值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 缓存List数据
     *
     * @param key      键
     * @param dataList List数据
     * @return 缓存的对象
     */
    public <T> long setCacheList(final String key, final List<T> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 缓存Map
     *
     * @param key     键
     * @param dataMap 数据
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * 缓存Set
     *
     * @param key     键
     * @param dataSet 数据
     * @return 缓存数据的对象
     */
    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        for (T t : dataSet) {
            setOperation.add(t);
        }
        return setOperation;
    }

    /**
     * 往Hash中存入数据
     *
     * @param key   键
     * @param hKey  Hash键
     * @param value 值
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /*-------------------------------------- Get --------------------------------------*/

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 对象
     */
    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 键
     * @return 数据集
     */
    public <T> List<T> getCacheList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 获得缓存的set
     *
     * @param key 键
     * @return 数据集
     */
    public <T> Set<T> getCacheSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 获得缓存的Map
     *
     * @param key 键
     * @return 对象
     */
    public <T> Map<String, T> getCacheMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key  键
     * @param hKey Hash键
     * @return 对象
     */
    public <T> T getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key      键
     * @param hashKeys Hash键集合
     * @return Hash对象集合
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hashKeys) {
        return redisTemplate.opsForHash().multiGet(key, hashKeys);
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> getListByKeys(final String pattern) {
        return redisTemplate.keys(pattern);
    }

    /*-------------------------------------- delete --------------------------------------*/

    /**
     * 删除单个对象
     *
     * @param key 键
     * @return 结果
     */
    public Boolean deleteObject(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection 多个对象
     * @return 结果
     */
    public Long deleteObjectList(final Collection collection) {
        return redisTemplate.delete(collection);
    }

    /**
     * 删除Hash中的数据
     *
     * @param key     键
     * @param hashKey hash键
     */
    public void delCacheMapValue(final String key, final String hashKey) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, hashKey);
    }

}
