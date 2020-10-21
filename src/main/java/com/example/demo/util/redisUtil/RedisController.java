package com.example.demo.util.redisUtil;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
public class RedisController {

    private static final String VISIT_MAX = "10";
    private static final String VISIT_INIT = "1";

    //存储数据格式为<String,String>
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //存储数据格式为<K,V>
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 永久插入redis
     **/
    @RequestMapping("/insertValueByKey")
    public String insertValueByKey(@RequestBody JSONObject jsonObject) {
        String key = jsonObject.getString("key");
        String value = stringRedisTemplate.opsForValue().get(key);
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, value);
        stringRedisTemplate.expire(key, 20, TimeUnit.SECONDS);
        return value;
    }

    /**
     * 根据key获取name
     **/
    @RequestMapping("/getValueByKey")
    public String getValueByKey(@RequestBody JSONObject jsonObject) {
        String key = jsonObject.getString("key");
        String value = stringRedisTemplate.opsForValue().get(key);
        return value;
    }

    @RequestMapping("/deleteByKey")
    public Boolean deleteByKey(@RequestBody JSONObject jsonObject) {
        String key = jsonObject.getString("key");
        Boolean flag = stringRedisTemplate.delete(key);
        return flag;
    }

    /**
     * 存储时效性数据 覆盖key存在的value   ,key 不存在时，执行结果false
     * 时效性30秒的数据
     **/
    @RequestMapping("/setIfPresentKeyAndValueOnScheduled")
    public Boolean setIfPresentKeyAndValueOnScheduled(@RequestBody JSONObject jsonObject) {
        //set一个值，设置有效时间为30秒，30后redis自动删除此key-value
        String key = jsonObject.getString("key");
        String value = jsonObject.getString("value");
        Boolean flag = stringRedisTemplate.opsForValue().setIfPresent(key, value, 30, TimeUnit.SECONDS);
        return flag;
    }

    @RequestMapping("/editVauleByKey")
    public Boolean editVauleByKey(@RequestBody JSONObject jsonObject) {
        //set一个值，设置有效时间为30秒，30后redis自动删除此key-value
        String key = jsonObject.getString("key");
        String value = jsonObject.getString("value");
        //获取此键剩余生效时间
        Long endTime = stringRedisTemplate.opsForValue().getOperations().getExpire(key);
        log.info("endTime = {}", endTime);
        Boolean flag = stringRedisTemplate.opsForValue().setIfPresent(key, value);
        return flag;
    }

    /**
     * 存储时效性数据 如果不存在key,才存储redis
     * 时效性30秒的数据
     **/
    @RequestMapping("/setIfAbsentKeyAndValueOnScheduled")
    public Boolean setIfAbsentKeyAndValueOnScheduled(@RequestBody JSONObject jsonObject) {
        String key = jsonObject.getString("key");
        String value = jsonObject.getString("value");
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, value, -1, TimeUnit.SECONDS);
        return flag;
    }

    /**
     * 插入map
     **/
    @RequestMapping("/setMap")
    public Boolean setMap(@RequestBody JSONObject jsonObject) {
        String key = jsonObject.getString("key");
        Map<String, String> value = jsonObject.getObject("value", Map.class);
        Boolean flag = redisTemplate.opsForValue().setIfAbsent(key, value);
        return flag;
    }

    /**
     * 获取对象
     **/
    @RequestMapping("/getObject")
    public Object getObject(@RequestBody JSONObject jsonObject) {
        String key = jsonObject.getString("key");
        Object object = redisTemplate.opsForValue().get(key);
        return object;
    }

}
