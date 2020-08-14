package com.example.demo.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.example.demo.Singleton.SerializeConfigSingleton;
import com.example.demo.entity.UserInfo;
import com.example.demo.entity.XwRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
@Slf4j
public class UserServiceImpl {
    /**
     * @Author longtao
     * @Date 2020/5/31
     * @Describe bean to String (String 是json格式)
     **/
    public String bean2Str(XwRequest xwRequest) {
        return JSON.toJSONString(xwRequest);
    }

    /**
     * @Author longtao
     * @Date 2020/5/31
     * @Describe bean to str
     * 将bean中属性：驼峰转为蛇形
     **/
    public String bean2SXStr(XwRequest xwRequest) {
        SerializeConfig config = SerializeConfigSingleton.getInstance();
//        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        return JSON.toJSONString(xwRequest, config);
    }

    /**
     * @Author longtao
     * @Date 2020/5/31
     * @Describe bean to map
     * 先转换为json格式的str,在通过JSON。parseObject(str,clazz);
     **/
    public Map<String, Object> bean2Map(XwRequest xwRequest) {
        //使用单例模式，避免性能问题
        SerializeConfig config = SerializeConfigSingleton.getInstance();
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        String str = JSON.toJSONString(xwRequest, config);
        return JSON.parseObject(str, Map.class);

    }

    /**
     * @Author longtao
     * @Date 2020/5/31
     * @Describe str to JSON
     **/
    public JSON str2Json(String str) {
        return JSON.parseObject(str);
    }
    /**
     * @Author longtao
     * @Date 2020/5/31
     * @Describe json to Bean
     * json的key是蛇形时，bean的属性是驼峰时，会自动转成驼峰
     **/
    public String json2Str(JSON json) {
//        SerializeConfig config = SerializeConfigSingleton.getInstance();
        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.CamelCase;
        return JSON.toJSONString(json, config);
    }
    /**
     * @Author longtao
     * @Date 2020/5/31
     * @Describe json to Bean
     * json的key是蛇形时，bean的属性是驼峰时，会自动转成驼峰
     **/
    public XwRequest json2Bean(JSON json){
//        ObjectMapper mapper = new ObjectMapper();
//        XwRequest xwRequest = new XwRequest();
//        try {
//            xwRequest =  mapper.readValue(json.toString(),XwRequest.class);
//        }catch (Exception e){
//            log.info("异常了！！！！！！！！！！！");
//        }
//        return xwRequest;
        return JSON.toJavaObject(json, XwRequest.class);
    }

    /**
     * @Author longtao
     * @Date 2020/5/31
     * @Describe json to bean
     * 复杂json转化为复杂bean
     * 内层bean需要重新赋值
     **/
    public UserInfo json2BeanUser(JSON json) {
        //此处获取到的json是xwRequest的json
        XwRequest xwRequest = JSON.toJavaObject(json, XwRequest.class);
        return JSON.toJavaObject(JSON.parseObject(xwRequest.getBody().toString()), UserInfo.class);
    }

}
