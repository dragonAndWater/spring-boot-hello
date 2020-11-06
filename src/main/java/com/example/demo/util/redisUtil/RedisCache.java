package com.example.demo.util.redisUtil;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * @Author longtao
 * @Date   2020/11/6
 * @Describe redis 实现数据库二级缓存
 **/
@Slf4j
public class RedisCache implements Cache {

    private static RedisTemplate<String, Object> redisTemplate;

    private final String id;

    /**
     * The {@code ReadWriteLock}.
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    public static void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisCache.redisTemplate = redisTemplate;
    }

    public RedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        log.debug("MybatisRedisCache:id=" + id);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        try {
            Long startTime = System.currentTimeMillis();
//            log.info("redis保存数据>>>>>>>>>>>>>>>>>>>>>>>>putObject: key=" + key + ",value=" + value);
            if (null != value)
                redisTemplate.opsForValue().set(key.toString(), value, 60, TimeUnit.SECONDS);//控制存放时间60s
            Long endTime = System.currentTimeMillis();
            log.info("redis保存数据耗时：{}",(endTime-startTime));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("redis保存数据异常！");
        }
    }

    @Override
    public Object getObject(Object key) {
        try {
            Long startTime = System.currentTimeMillis();
//            log.info("redis获取数据>>>>>>>>>>>>>>>>>>>>>>>>getObject: key=" + key);
            if (null != key){
                Object o = redisTemplate.opsForValue().get(key.toString());
                Long endTime = System.currentTimeMillis();
                log.info("redis获取数据耗时：{}",(endTime-startTime));
                return o;
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("redis获取数据异常！");
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        try {
            if (null != key)
                return redisTemplate.expire(key.toString(), 1, TimeUnit.DAYS);//设置过期时间
        } catch (Exception e) {
            e.printStackTrace();
            log.error("redis获取数据异常！");
        }
        return null;
    }

    @Override
    public void clear() {
        Long size = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                Long size = redisConnection.dbSize();
                //连接清除数据
                redisConnection.flushDb();
                redisConnection.flushAll();
                return size;
            }
        });
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>clear: 清除了" + size + "个对象");
    }

    @Override
    public int getSize() {
        Long size = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException {
                return connection.dbSize();
            }
        });
        return size.intValue();
    }
}