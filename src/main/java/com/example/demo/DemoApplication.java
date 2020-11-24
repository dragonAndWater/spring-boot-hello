package com.example.demo;

import com.example.demo.base.config.ServerPortConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DemoApplication {
    private static final String red = "\033[31;4m";
    private static final String end = "\033[0m";

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
//        applicationContext.getBean(SocketServer.class);
        System.out.println("======......项目启动成功......======"+red + ServerPortConfig.host+":"+ServerPortConfig.serverPort + end);

    }

}
