package com.example.demo.base.model.baseResponse;

import com.example.demo.base.Enum.ResultEnum;
import com.example.demo.base.exception.CheckException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author long_tao
 */
@Data
public class BaseResponse<T> {
    /**
     * 返回码
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code;
    /**
     * 返回信息
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;
    /**
     * 统计总数
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer count;
    /**
     * 返回数据
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public BaseResponse() {
    }

    public BaseResponse(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(String code, String msg, Integer count, T data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public BaseResponse(ResultEnum result, T data) {
        this.code = result.getCode();
        this.msg = result.getMsg();
        this.data = data;
    }

    public BaseResponse(ResultEnum result, Integer count, T data) {
        this.code = result.getCode();
        this.msg = result.getMsg();
        this.count = count;
        this.data = data;
    }

    public BaseResponse(ResultEnum result) {
        this.code = result.getCode();
        this.msg = result.getMsg();
    }

    public BaseResponse(ResultEnum result, String msg) {
        this.code = result.getCode();
        this.msg = result.getMsg() + msg;
    }

    public BaseResponse(CheckException checkException) {
        this.code = checkException.getCode();
        this.msg = checkException.getMsg();
    }
}
