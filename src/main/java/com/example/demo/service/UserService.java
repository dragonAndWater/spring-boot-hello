package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.HeadModel;
import com.example.demo.entity.Toy;
import com.example.demo.entity.UserInfo;
import com.example.demo.entity.XwRequest;
import com.example.demo.serviceImpl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class UserService {
    @Resource
    private UserServiceImpl userSer;

    @Value("${host_ip}")
    private String HOST_IP;


    @ResponseBody
    @RequestMapping("/hello")
    @Async
    public String setHello() {
        String str = "Hello World !";
        Toy t = new Toy();
        t.setPrice(new BigDecimal("1000000.00"));
        return getPrice(t);
    }

    public static String getPrice(@Valid Toy t){
        System.out.println("t.getPrice() = "+t.getPrice());
        return t.getPrice().toString();
    }

    @ResponseBody
    @RequestMapping("/bean2Str")
    public String bean2Json() {
        return userSer.bean2Str(setRequest());
    }

    @ResponseBody
    @RequestMapping("/bean2SXStr")
    public String bean2SXStr() {
        return userSer.bean2SXStr(setRequest());
    }

    @ResponseBody
    @RequestMapping("/bean2Map")
    public Map bean2Map() {
        return userSer.bean2Map(setRequest());
    }

    @ResponseBody
    @RequestMapping("/json2Bean")
    public XwRequest json2Bean() {
        JSON json = JSON.parseObject(getStr());
        return userSer.json2Bean(json);
    }

    @ResponseBody
    @RequestMapping("/json2BeanUser")
    public UserInfo json2BeanUser() {
        JSON json = JSON.parseObject(getStr());
        return userSer.json2BeanUser(json);
    }

    @ResponseBody
    @RequestMapping("/json2Str")
        public String json2Str() {
        JSON json = JSON.parseObject(getStr());
        return userSer.json2Str(json);
    }
    @ResponseBody
    @RequestMapping("/getHostIp")
    public String getHostIp() {
        log.info("获取host_ip = "+HOST_IP);
        return HOST_IP;
    }
    @ResponseBody
    @RequestMapping("/str2Json")
    public String str2Json() {
        log.info("获取host_ip = "+HOST_IP);
        return HOST_IP;
    }

    public static XwRequest setRequest() {
        XwRequest requset = new XwRequest();
        HeadModel headModel = new HeadModel();
        headModel.setRetCode("000000");
        headModel.setRetMsg("交易成功");
        headModel.setStatus("success");
        requset.setHead(headModel);


        UserInfo userModel = new UserInfo();
        userModel.setName("long_tao");
        userModel.setPhoneNo("15199999999");
        userModel.setJobName("程序员");

        List<Toy> toyList = new LinkedList<Toy>();
        Toy toy1 = new Toy();
        toy1.setToyName("小汽车");
        toy1.setToyPrice("15.00");
        toyList.add(toy1);

        Toy toy2 = new Toy();
        toy2.setToyName("变形金刚");
        toy2.setToyPrice("35.50");
        toyList.add(toy2);

        userModel.setToyList(toyList);
        requset.setBody(userModel);

        return requset;
    }

    public static String getStr() {
        return "{\n" +
//                "  \"body\": {\n" +
//                "    \"job_mame\": \"程序员\",\n" +
//                "    \"name\": \"long_tao\",\n" +
//                "    \"phone_no\": \"15199999999\",\n" +
//                "    \"toy_list\": [\n" +
//                "      {\n" +
//                "        \"toy_name\": \"小汽车\",\n" +
//                "        \"toy_price\": \"15.00\"\n" +
//                "      },\n" +
//                "      {\n" +
//                "        \"toy_name\": \"变形金刚\",\n" +
//                "        \"toy_price\": \"35.50\"\n" +
//                "      }\n" +
//                "    ]\n" +
//                "  },\n" +
                "  \"head\": {\n" +
                "    \"ret_code\": \"000000\",\n" +
                "    \"ret_msg\": \"交易成功\",\n" +
                "    \"status\": \"success\"\n" +
                "  }\n" +
                "}";
    }

}
