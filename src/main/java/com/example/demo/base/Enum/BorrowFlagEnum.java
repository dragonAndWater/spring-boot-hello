package com.example.demo.base.Enum;

/**
 * @Author longtao
 * @Date 2020/10/10
 * @Describe 枚举--书籍类别
 **/
public enum BorrowFlagEnum {
    RETURN("in", "已归还"),

    LEN_OUT("out", "已借出");


    /**
     * 返回码
     **/
    private String code;
    /**
     * 返回信息
     **/
    private String msg;

    BorrowFlagEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 根据枚举的msg 获取对应枚举
     **/
    public static BorrowFlagEnum getEnumBymsg(String msg) {
        for (BorrowFlagEnum bookTypeEnum : BorrowFlagEnum.values()) {
            if (bookTypeEnum.getMsg().equals(msg)) {
                return bookTypeEnum;
            }
        }
        return null;
    }

}
