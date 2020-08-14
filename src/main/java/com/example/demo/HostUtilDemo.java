package com.example.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class HostUtilDemo {
    public static void main(String[] args) {
        //获取本机IP
        try {
            InetAddress in = InetAddress.getLocalHost();
            System.out.println("ip = "+in.getHostAddress());
            System.out.println("name = "+ in.getHostName());
            byte[] bytes = in.getAddress();

            System.out.println("address = "+ Arrays.toString(bytes));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
