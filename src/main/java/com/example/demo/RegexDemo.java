package com.example.demo;


import java.util.regex.Pattern;

public class RegexDemo {
    public static void main(String[] args) {
//        System.out.println(IDutil.getInstance().getId());

        String regx = "\\W*";
        String str1 = "读者ID";
        String str2 = "_1234_56789_者asdaca_sd_qwe_";
        System.out.println(Pattern.matches(regx,str1));
        System.out.println(Pattern.matches("\\w*",str2));

//        System.out.println("-----------------测试中英文冒号：:");
//        String regex1 = ":|：";
//        String text1 = "2:拆除报警";
//        String[] arrStr1 = text1.split(regex1);
//        for (String s : arrStr1) {
//            System.out.println(s);
//        }
//
//        System.out.println("-----------------测试中英文波浪号~~");
//        String regex2 = "~|～";
//        String text2 = "-32768~32767～9999";
//        String[] arrStr2 = text2.split(regex2);
//        for (String s : arrStr2) {
//            System.out.println(s);
//        }
    }
}
