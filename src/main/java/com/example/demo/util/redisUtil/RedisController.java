package com.example.demo.util.redisUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
public class RedisController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("/getName")
    public String getName() {
//        Jedis jedis = new Jedis("10.108.130.139");
//        String name = jedis.get("name");
        String name = stringRedisTemplate.opsForValue().get("five");
        log.info("name = " + name);
        return name;
    }

    @RequestMapping("/setKey")
    public String setKey() {
        //
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
        //set一个值，设置有效时间为30秒，30后redis自动删除此key-value
        redisTemplate.opsForValue().set("five", "00005",30, TimeUnit.SECONDS);
        String getValue = (String) redisTemplate.opsForValue().get("five");
        log.info("value = " + getValue);
        return getValue;
    }

    @RequestMapping("/setKey2")
    public String setkey2(){
        stringRedisTemplate.opsForValue().set("third","0003");
        String getValue = stringRedisTemplate.opsForValue().get("third");
        return getValue;
    }

}
