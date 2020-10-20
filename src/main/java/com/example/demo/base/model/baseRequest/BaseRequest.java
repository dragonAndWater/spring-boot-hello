package com.example.demo.base.model.baseRequest;

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
    @NotNull
    private String systemNo;

    /**
     * 请求流水号
     **/
    @NotNull
    private String  seqNo;

    /**
    * 请求时间
    **/
    @NotNull
    private Date tranDate;

    /**
     * 请求业务体
     **/
    @NotNull
    private T data;
}
