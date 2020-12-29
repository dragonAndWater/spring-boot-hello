package com.example.demo;


public class dateDemo {

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
        System.out.println("startTime = "+startTime);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.err.println("线程睡眠异常");
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("相隔时间 = "+(endTime-startTime));
        System.out.println("endTime = "+endTime);

    }
}
