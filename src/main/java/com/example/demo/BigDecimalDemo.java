package com.example.demo;

import java.math.BigDecimal;

public class BigDecimalDemo {
    public static void main(String[] args) {
        BigDecimal num1 = new BigDecimal("100.00");
        BigDecimal num2 = new BigDecimal("100.00");
        BigDecimal num3 = new BigDecimal("200.00");

        System.out.println(num1.compareTo(num2));// num1 = num2  0
        System.out.println(num1.compareTo(num3));// num1 < num3  -1
        System.out.println(num3.compareTo(num1));// num3 > num1  1

    }
}
