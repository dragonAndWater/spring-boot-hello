package com.example.demo.threadConfig;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Date;
import java.util.Timer;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class ThreadConfig implements AsyncConfigurer  {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);//线程池维护线程饿最少数量
        executor.setMaxPoolSize(15);//线程池维护线程的最大数量
        executor.setQueueCapacity(25);//线程池所使用的的缓冲队列
        executor.setThreadNamePrefix("spring-boot-hello"+new Date()+new Timer()+"123");//线程名前缀
        executor.initialize();//初始化
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
