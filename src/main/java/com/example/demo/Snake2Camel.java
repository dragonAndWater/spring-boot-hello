package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Snake2Camel {
    private static String FILE_PATH = "E:/1.IDEA/Workspace/spring-boot-hello/src/main/java/com/example/demo/";
    public static void main(String[] args) {
        //读取文件
        try {
            FileReader fr = new FileReader(FILE_PATH + "SnakeFile");
            BufferedReader bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                //正则表达式
//                Pattern p = Pattern.compile(REGEX);
//                Matcher m = p.matcher(str);
//                m.find();
                //获取属性名
                str = str.trim();
                String[] strs = str.split(" ");
                //获取属性名--蛇形转驼峰
                String str1 = getNewString(strs[0]);

                System.out.println(str1);
                //获取数据类型-类型映射
//                if (strs[1].contains("(")) {
//                    strs[1] = strs[1].substring(0, strs[1].indexOf('('));
//                }
//                String str2 = getDataType(strs[1]);
//
//                //获取属性注释
//                String str3 = strs[2];
//                getMessage(str1,str2,str3);

            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //按行转换，并输出控制台
    }

    /**
     * @Author longtao
     * @Date 2020/9/4
     * @Describe 蛇型转驼峰
     **/
    public static String getNewString(String str) {
        str = str.toLowerCase();
        int index = str.indexOf("_");
        while (index != -1) {
            str = str.replaceFirst(str.substring(index, index + 2), str.substring(index + 1, index + 2).toUpperCase());
            index = str.indexOf("_");
        }
        return str;
    }
}
