package com.example.demo;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class Snake2Camel_a_b_c {
    private static String FILE_PATH = "E:/1.IDEA/Workspace/spring-boot-hello/src/main/java/com/example/demo/";
    private static String REGEX = "\\w*";

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

                //获取数据类型-类型映射
                if (strs[1].contains("(")) {
                    strs[1] = strs[1].substring(0, strs[1].indexOf('('));
                }
                String str2 = getDataType(strs[1]);

                //获取属性注释
                String str3 = strs[2];
                getMessage(str1,str2,str3);

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

    public static void getMessage(String str1, String str2, String str3) {
        System.out.println("/**");
        System.out.println(" * " + str3);
        System.out.println(" **/");
        System.out.println("private " + str2 + " " + str1+";");
    }

    public static String getDataType(String str) {
        switch (str) {
            //默认放到default里面
//            case "varchar":
//                return "String";
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
                return "Integer";
            default:
                return "String";
        }
    }

}
