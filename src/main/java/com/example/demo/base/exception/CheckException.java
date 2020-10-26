package com.example.demo.base.exception;

import com.example.demo.base.Enum.ResultEnum;
import lombok.Data;
/**
 * @Author longtao
 * @Date   2020/10/23
 * @Describe 自定义异常实现 检查失败返回码
 **/
@Data
public class CheckException extends Exception {
    private String code;
    private String msg;


    public CheckException(ResultEnum resultEnum,String msg){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg()+":" + msg;
    }

    public CheckException(ResultEnum resultEnum){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }
}
