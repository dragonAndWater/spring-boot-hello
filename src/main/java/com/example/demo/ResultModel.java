package com.example.demo;

import lombok.Data;

@Data
public class ResultModel<T> {
    private Integer ret;
    private String msg;
    private String codeMsg;
    private T data;
}
