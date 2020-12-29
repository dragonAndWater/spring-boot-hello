package com.example.demo;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author long_tao
 */
@Slf4j
public class Snake2Camel_DDL {
    private static String FILE_PATH = "E:/1.IDEA/Workspace/spring-boot-hello/src/main/java/com/example/demo/";

    public static void main(String[] args) {
        //读取文件
        try {
            FileReader fr = new FileReader(FILE_PATH + "SnakeFile");
            BufferedReader bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                //去调前置空格
                str = str.trim();
//                log.info("--" + str);
                //获取属性名-蛇形转驼峰
                String str1 = getNewString(str.substring(str.indexOf('`') + 1, str.lastIndexOf('`')));
//                log.info("str1 = " + str1);
                //获取属性类型
                String[] strs = str.split(" ");
//                log.info("转换前:{}", strs[1]);
                if (strs[1].contains("(")) {
                    strs[1] = strs[1].substring(0, strs[1].indexOf('('));
                }
                String str2 = getDataType(strs[1]);
//                log.info("str2 = " + str2);

                //获取对应属性注释
                String s = str.substring(0,str.lastIndexOf('\''));
                String str3 = s.substring(s.lastIndexOf('\'')+1);
//                log.info("str3 = " + str3);
                //生成属性
                getMessage(str1, str2, str3);
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


//
    }

    public static void getMessage(String str1, String str2, String str3) {
        System.out.println("/**");
        System.out.println(" * " + str3);
        System.out.println(" **/");
        System.out.println("private " + str2 + " " + str1+";");
    }

    /**
     * @Author longtao
     * @Date   2020/9/4
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

    public static String getDataType(String str) {
        switch (str) {
            //根据mysql数据类型转换成java数据类型
            case "bigint":
                return "Long";
            case "decimal":
                return "BigDecimal";
            case "timestamp":
            case "date":
                return "Date";
            case "double":
                return "Double";
            case "float":
                return "Float";
            case "int":
            case "tinyint":
                return "Integer";
            default:
                return "String";
        }
    }

}
