package com.example.demo;

import com.example.demo.base.model.baseResponse.BaseResponse;
import com.sun.org.apache.xpath.internal.operations.Equals;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class Snake2Camel_insert {
    private static String FILE_PATH = "E:/1.IDEA/Workspace/spring-boot-hello/src/main/java/com/example/demo/";

    public static void main(String[] args) {
        //读取文件
        try {
            FileReader fr = new FileReader(FILE_PATH + "SnakeFile");
            BufferedReader bf = new BufferedReader(fr);
            String str;
            StringBuffer keyStr = new StringBuffer();
            StringBuffer valueStr = new StringBuffer();
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                //去调前置空格
                str = str.trim();
                if("{".equals(str)){
                    keyStr = keyStr.append("INSERT INTO t_car_info (");
                    valueStr = valueStr.append("VALUES(");
                    continue;
                }
                if("},".equals(str)){
                    System.out.println(keyStr.substring(0,keyStr.length()-1)+")"+valueStr.substring(0, valueStr.length()-1)+");");
                    keyStr = new StringBuffer();
                    valueStr = new StringBuffer();
                    continue;
                }
                str = str.replaceAll("\"","");
                str = str.replaceAll(",","");
                String[] arrStr = str.split(":");
                keyStr.append(arrStr[0].trim()).append(",");
                valueStr.append('\'').append(arrStr[1].trim()).append('\'').append(",");
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
