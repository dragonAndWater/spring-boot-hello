package com.example.demo.util.socketUtil;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
@Slf4j
public class SocketServer {
    private static final String IP = "127.0.0.1";
    private static final int PORT = 50005;

    public static void main(String[] args) {
        try (ServerSocket socket = new ServerSocket(PORT)) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
