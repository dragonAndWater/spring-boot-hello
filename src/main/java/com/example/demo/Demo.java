package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/15.
 */
@Slf4j
public class Demo {

    public static String getUrlByMap(String url, Map<String,Object> map){
        if(StringUtils.isEmpty(url) || map == null){
            return url;
        }
        StringBuilder stringBuilder = new StringBuilder(url);
        if(!map.isEmpty()){
            stringBuilder.append("?");
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                stringBuilder.append(entry.getKey());
                stringBuilder.append("=");
                stringBuilder.append(entry.getValue());
                stringBuilder.append("&");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        //调用的地址
        String ip = "http://120.76.69.92:7515";
        //接口名称
        String urlName = "/VehicleData/GetVehicleBaseData.json";
        //调用地址
        String apiAddress = ip+urlName;
        //参数
        Map<String, Object> map = new HashMap<>();
        //调用申请api密匙接口
        map.put("key", "654adasdas65d4a6farfafas");
        map.put("type", 1);
        String url = getUrlByMap(apiAddress, map);
        try {
        	url = url.replaceAll(" ", "%20");
            log.info("请求参数：{}",url);
            String result = httpGet(url);
            log.info("响应结果：{}",result);
            //获得结果
            System.err.println(result);
        } catch (Exception e) {
            System.err.println("报错" + e.getLocalizedMessage());
        }
    }

    public  static String httpGet(String url) throws Exception {
        // 调用接口，打开链接
        String inputLine = "";
        URL fixposition = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) fixposition.openConnection();// 打开连接
        //必须设置时间了，不然卡死
        connection.setConnectTimeout(1000);
        connection.setReadTimeout(1000);
        connection.connect();// 连接会话
        BufferedReader in = new BufferedReader(new InputStreamReader(fixposition.openStream(), "UTF-8"));
        try {
            String readLine;
            while ((readLine = in.readLine()) != null) {
                inputLine = inputLine + readLine;
            }
        } finally {
            in.close();
        }
        return inputLine;

    }
}
