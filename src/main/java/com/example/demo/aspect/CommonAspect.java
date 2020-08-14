package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CommonAspect {
    /**
     * 切入点
     */
    @Pointcut("@annotation(com.example.demo.annotation.CommonAnnotation) ")
    public void entryPoint() {
    }

    @Before("entryPoint()")
    public boolean beforeExecute(JoinPoint jp){
        Object[] os = jp.getArgs();
        for (Object o : os) {
            log.debug("获取到的参数为:{}",o);
        }
        return true;
    }

}
