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
public class text2ABC {
    private static String FILE_PATH = "E:/1.IDEA/Workspace/spring-boot-hello/src/main/java/com/example/demo/";

    public static void main(String[] args) {
        //读取文件
        try {
            FileReader fr = new FileReader(FILE_PATH + "textFile");
            BufferedReader bf = new BufferedReader(fr);
            String s1 = null;
            String s2;
            String s3;
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                //去调前置空格
                str = str.trim();
//                log.info("--" + str);
                //获取属性名-蛇形转驼峰
                if(str.indexOf("/**")>0){
                    continue;
                }
                if(str.indexOf("*/")>0){
                    continue;
                }
                if(isContainChinese(str)){
                    s1 = str.substring(2);
                    continue;
                }
                if(str.indexOf(";")>0){
                    String[] strs = str.split(" ");
                    s2 = strs[1];
                    s3 = strs[2];
                    s3 = s3.substring(0,s3.length()-1);
                    getMessage(s1,s2,s3);
                    s1 = null;
                    s2 = null;
                    s3 = null;
                }
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


//
    }
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public static void getMessage(String str1, String str2, String str3) {
        System.out.println("|"+str3+"|"+str2+"|"+str1+"|");
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
