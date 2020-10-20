package com.example.demo.base.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
@Order(1)
public class BaseBeforeAspection {

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.example.demo.base.annonation.BaseBeforeAnnotation) ")
    public void entryPoint() {
    }

    /**
     * @return
     * @Describe 前置切面
     */
    @Before("entryPoint()")
    public void beforeMethod(JoinPoint joinPoint) {
        log.debug("----------------------------------------------------------------------------------------------------start");
        log.debug("------BaseBeforeAspect---获取到请求参数------");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Object[] args = joinPoint.getArgs();
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

    }

    /**
     * 后置切面
     **/
    @AfterReturning(value = "entryPoint()", returning = "o")
    public Object afterExecute(Object o) {
        log.debug("---commonAfter返回参数:{}", JSON.toJSONString(o));
        log.debug("----------------------------------------------------------------------------------------------------end");
        return o;
    }

}
