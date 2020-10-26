package com.example.demo.base.model.baseResponse;

import com.example.demo.base.Enum.ResultEnum;
import com.example.demo.base.exception.CheckException;
import lombok.Data;

@Data
public class BaseResponse<T> {
    private String code;

    private String msg;

    private T data;

    public BaseResponse() {
    }

    public BaseResponse(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(ResultEnum result, T data) {
        this.code = result.getCode();
        this.msg = result.getMsg();
        this.data = data;
    }

    public BaseResponse(ResultEnum result) {
        this.code = result.getCode();
        this.msg = result.getMsg();
        this.data = (T) "";
    }
    public BaseResponse(ResultEnum result,String msg) {
        this.code = result.getCode();
        this.msg = result.getMsg()+msg;
        this.data = (T) "";
    }
    public BaseResponse(CheckException checkException) {
        this.code = checkException.getCode();
        this.msg = checkException.getMsg();
        this.data = (T) "";
    }
}
