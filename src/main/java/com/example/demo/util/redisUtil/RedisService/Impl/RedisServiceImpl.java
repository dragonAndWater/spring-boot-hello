package com.example.demo.util.redisUtil.RedisService.Impl;

import com.example.demo.util.redisUtil.RedisService.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @Author longtao
     * @Date 2020/10/20
     * @Describe 更新失效性数据  保留剩余有效时间
     **/
    @Override
    public synchronized Boolean updateVaildValue(String key, String value) {
        //获取剩余时间 单位秒
        Long balanceSeconds = stringRedisTemplate.opsForValue().getOperations().getExpire(key);
        if (balanceSeconds > 0) {
            return stringRedisTemplate.opsForValue().setIfPresent(key, value, balanceSeconds, TimeUnit.SECONDS);
        }
        return false;
    }
    /**
     * @Author longtao
     * @Date   2020/10/20
     * @Describe 判断 最大访问次数 > userId当前访问次数
     **/
    @Override
    public synchronized Boolean judgeMaxTimesByUserId(String key, String min, String max) {
        //获取key的值，为null时则插入新的
        //不为null时，取出数据进行判断：是否达到最大值
        String value = stringRedisTemplate.opsForValue().get(key);
        if(StringUtils.isEmpty(value)){
            stringRedisTemplate.opsForValue().setIfAbsent(key,min,24,TimeUnit.HOURS);
            return true;
        }
        //最大次数 <= 当前访问次数
        if(new BigDecimal(max).compareTo(new BigDecimal(value)) <= 0 ){
            return false;
        }
        return updateVaildValue(key,new BigDecimal(value).add(new BigDecimal(1)).toString());
    }
}
