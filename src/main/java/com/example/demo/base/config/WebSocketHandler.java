package com.example.demo.base.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Websocket处理器 
 */
@Component
public class WebSocketHandler extends TextWebSocketHandler {


    private final static Logger LOGGER = LoggerFactory.getLogger(WebSocketHandler.class);


    //已建立连接的用户  
    private final  static  CopyOnWriteArraySet<WebSocketSession> deviceNBids = new CopyOnWriteArraySet<>();

    /**
     * 处理前端发送的文本信息 
     * js调用websocket.send时候，会调用该方法 
     * 
     * @param session 
     * @param message 
     * @throws Exception 
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    }
  
  
    /**
     * 当新连接建立的时候，被调用 
     * 连接成功时候，会触发页面上onOpen方法 
     * 
     * @param session 
     * @throws Exception 
     */
    @Override  
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        deviceNBids.add(session);
    }
  
    /**
     * 当连接关闭时被调用 
     * 
     * @param session 
     * @param status 
     * @throws Exception 
     */
    @Override  
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        deviceNBids.remove(session);
    }
  
    /**
     * 传输错误时调用 
     * 
     * @param session 
     * @param exception 
     * @throws Exception 
     */
    @Override  
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }  
        deviceNBids.remove(session);
    }
  
    /**
     * 给所有在线用户发送消息 
     * 
     * @param message 
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : deviceNBids) {
            try {  
                if (user.isOpen()) {  
                    user.sendMessage(message);  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }

    /**
     * 给设备调试页发送消息
     * 
     */
    public void sendMessageToDebugger(String deviceNBid, TextMessage message) {
        for (WebSocketSession user : deviceNBids) {
            try {
                if (user.getAttributes().get("deviceId").equals(deviceNBid)) {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给设备调试页发送消息
     *
     */
    public boolean sendMessageToDebugger(String deviceId, Queue<String> queue) {
        if(queue != null){
            Iterator<WebSocketSession> iterator = deviceNBids.iterator();
            while (iterator.hasNext()){
                WebSocketSession webSocketSession = iterator.next();
                if (webSocketSession.getAttributes().get("deviceId").equals(deviceId)) {
                    try {
                        if (webSocketSession.isOpen()) {
                            while(queue.size() > 0){
                                String str = queue.poll();
                                if(str != null){
                                    TextMessage textMessage = new TextMessage(str);
                                    webSocketSession.sendMessage(textMessage);
                                }
                            }
                            return true;
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                }else{

                }
            }
        }
        return false;
    }
    public boolean sendMsg(String msg) {
        System.out.println("开始发送消息-- ");
        Iterator<WebSocketSession> iterator = deviceNBids.iterator();
        while (iterator.hasNext()){
            WebSocketSession webSocketSession = iterator.next();
                try {
                    if (webSocketSession.isOpen()) {

                            if(msg != null){
                                TextMessage textMessage = new TextMessage(msg);
                                webSocketSession.sendMessage(textMessage);
                                System.out.println("发送成功-- ");
                            }
                        return true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
        }
        return false;
    }
}