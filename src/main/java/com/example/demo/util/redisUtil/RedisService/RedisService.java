package com.example.demo.util.redisUtil.RedisService;


public interface RedisService {
    /**
     * 更新时效性数据--String类型
     **/
    Boolean updateVaildValue(String key, String value);

    /**
     * 判断当前用户是否达到访问最大次数
     **/
    Boolean judgeMaxTimesByUserId(String key, String min, String max);
}
