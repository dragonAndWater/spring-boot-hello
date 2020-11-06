package com.example.demo.base.model.baseRequest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author longtao
 * @Date 2020/9/4
 * @Describe 基本请求类
 **/
@Data
public class BaseRequest<T> {
    /**
     * 请求系统号
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String systemNo;

    /**
     * 请求流水号
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String  seqNo;

    /**
    * 请求时间
    **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date tranDate;

    /**
     * 请求业务体
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
}
