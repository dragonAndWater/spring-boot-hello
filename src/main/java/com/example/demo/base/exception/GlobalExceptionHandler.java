package com.example.demo.base.exception;

import com.example.demo.base.Enum.Msg;
import com.example.demo.base.model.baseResponse.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
/**
 * @Author longtao
 * @Date   2020/12/17
 * @Describe GlobalExceptionHandler中做了异常处理 所以一般情况下不需要再去手动捕获异常并return new BaseResponse(ResultEnum.ERROR);
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = CheckException.class)
    public BaseResponse handleException(CheckException e) {
        e.printStackTrace();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(e.getCode());
        baseResponse.setMsg(e.getMsg());
        return baseResponse;
    }

    /**
     * 400
     *
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Object handleException400(HttpServletRequest req, HttpMessageNotReadableException e) {
        e.printStackTrace();
        return new BaseResponse(Msg.ERROR, "请求的参数中有数据类型或数据格式错误");
    }

    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public BaseResponse exceptionHandler(HttpServletRequest req, Exception e) {
        e.printStackTrace();
        log.error("其他异常！原因:", e);
        return new BaseResponse(Msg.ERROR);
    }
}
