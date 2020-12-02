package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class JSONArrayDemo {
    public static void main(String[] args) {
        String str = "{\n" +
                "  \"ret\": 0,\n" +
                "  \"msg\": \"OK\",\n" +
                "  \"code_msg\": \"OK!\",\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"imei\": \"862459047225011\",\n" +
                "      \"acc_seconds\": 5905\n" +
                "    },\n" +
                "    {\n" +
                "      \"imei\": \"862459045314650\",\n" +
                "      \"acc_seconds\": 894\n" +
                "    },\n" +
                "    {\n" +
                "      \"imei\": \"668683000215250\",\n" +
                "      \"acc_seconds\": 65491302\n" +
                "    },\n" +
                "    {\n" +
                "      \"imei\": \"668683000215260\",\n" +
                "      \"acc_seconds\": 65491526\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(str);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        log.info("JSONArray------");
        jsonArray.forEach(o ->{
            log.info("o = {}",o);
        });

        log.info("List<Object>------");
        List<Object> list = jsonObject.getJSONArray("data");
        list.forEach(o ->{
            log.info("o = {}",o);
        });

        ResultModel resultModel = JSONObject.parseObject(jsonObject.toJSONString(),ResultModel.class);
        log.info("ret = {}",resultModel.getRet());
        log.info("code_msg ={}",resultModel.getCodeMsg());

    }
}
