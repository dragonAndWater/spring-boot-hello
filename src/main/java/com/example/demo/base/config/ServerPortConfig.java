package com.example.demo.base.config;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取项目的ip和端口信息
 * @author witty
 */
@Component
public class ServerPortConfig implements ApplicationListener<WebServerInitializedEvent> {

    public static int serverPort;
    public static String host;


    @Override
    public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {
        ServerPortConfig.serverPort = webServerInitializedEvent.getWebServer().getPort();
        try {
            ServerPortConfig.host = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.err.println("获取ip异常");
        }
    }
}
