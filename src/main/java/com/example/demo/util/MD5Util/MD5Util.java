package com.example.demo.util.MD5Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    //待加密的密码
    public static String password = "zjb0425";

    public static void main(String args[]) {
        //结果字符串
       Long time = System.currentTimeMillis()/1000;
       String str = "C1ZIOxqRbV3CAZhoB431FNm0EXSa&MBX";
        System.out.println("time = {}"+time);
        System.out.println("signature = {}"+get32LowMd5(get32LowMd5(str)+time));
    }

    public static String get32LowMd5(String str){
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
//            System.out.println("MD5(" + password + ",32小写) = " + result);
//            System.out.println("MD5(" + password + ",32大写) = " + result.toUpperCase());
//            System.out.println("++++++++++++++++++++++++各位大哥借过+++++++++++++++++++++++");
//            System.out.println("MD5(" + password + ",16小写) = " + buf.toString().substring(8, 24));
//            System.out.println("MD5(" + password + ",16大写) = " + buf.toString().substring(8, 24).toUpperCase());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}