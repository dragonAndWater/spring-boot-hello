package com.example.demo.base.exception;

import com.example.demo.base.Enum.Check;
import com.example.demo.base.Enum.Msg;
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


    public CheckException(Msg resultEnum, String msg){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg()+":" + msg;
    }

    public CheckException(Msg msg){
        this.code = msg.getCode();
        this.msg = msg.getMsg();
    }

    public CheckException(Check check){
        this.code = check.getCode();
        this.msg = Check.CHECK_MUST+check.getMsg();
    }
}
