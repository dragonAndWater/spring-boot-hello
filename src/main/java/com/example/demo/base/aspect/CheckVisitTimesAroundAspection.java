package com.example.demo.base.aspect;

import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.base.Enum.ResultEnum;
import com.example.demo.base.model.baseModel.BaseModel;
import com.example.demo.base.model.baseRequest.BaseRequest;
import com.example.demo.base.model.baseResponse.BaseResponse;
import com.example.demo.util.redisUtil.RedisService.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
@Order(2)
public class CheckVisitTimesAroundAspection {

    @Autowired
    private RedisService redisService;
    /**
     * 切入点
     */
    @Pointcut("@annotation(com.example.demo.base.annonation.CheckVisitTimesAroundAnnotation) ")
    public void entryPoint() {
    }

    /**
     * @return
     * @Describe 前置切面
     */
    @Around("entryPoint()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("------检查userId访问次数限制------start");
        Object[] args = proceedingJoinPoint.getArgs();
        //打印入口方法以及入参
        try {
            for (Object arg : args) {
                Boolean flag = judgeVisitTimesByUserId(arg);
                if(!flag){
                    return new BaseResponse<>(ResultEnum.CHECK_USER_ID_VISIT_TIMES);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("不能打印请求参数");
        }
        //执行请求方法
        Object o = null;
        try {
            o = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        log.info("------检查userId访问次数限制------end");
        return o;

    }

    //检查userId请求接口限制
    public Boolean judgeVisitTimesByUserId(Object object){
        BaseModel baseModel = JSONObject.parseObject(JSONObject.toJSONString(object),BaseModel.class);
        String userId = baseModel.getUserId();
        if(!StringUtils.isEmpty(userId)){
            return redisService.judgeMaxTimesByUserId(userId,"1","10");
        }
        return true;
    }
}
