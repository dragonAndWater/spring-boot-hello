package com.example.demo.base.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
@Order(1)
public class BaseAroundAspection {

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.example.demo.base.annonation.BaseAroundAnnotation) ")
    public void entryPoint() {
    }

    /**
     * @return
     * @Describe 前置切面
     */
    @Around("entryPoint()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) {
        log.debug("----------------------------------------------------------------------------------------------------start");
        log.info("------BaseBeforeAspect---获取到请求参数------");
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Object[] args = proceedingJoinPoint.getArgs();
        //打印入口方法以及入参
        log.debug("---入口方法:{}", method.getDeclaringClass().getName());
        log.debug("---请求方法:{}", method.getName());
        try {
            for (Object arg : args) {
                log.debug("---请求参数:{}", JSON.toJSONString(arg));
            }
        } catch (Exception e) {
            log.debug("不能打印请求参数");
        }
        //执行请求方法
        log.debug("------开始执行请求方法:{}",method.getName());
        Long startTime = System.currentTimeMillis();
        Object o = null;
        try {
            o = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Long endTime = System.currentTimeMillis();

        log.debug("---请求方法:{}---执行时间:{}",method.getName(),endTime-startTime);
        log.debug("---返回参数:{}",JSON.toJSONString(o));
        log.debug("----------------------------------------------------------------------------------------------------end");
        return o;

    }

}
