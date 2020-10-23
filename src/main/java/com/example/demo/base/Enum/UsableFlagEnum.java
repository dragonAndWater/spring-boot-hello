package com.example.demo.base.Enum;

/**
 * @Author longtao
 * @Date 2020/10/10
 * @Describe 枚举--书籍类别
 **/
public enum UsableFlagEnum {
    UN_BIND("0", "未绑定卡"),

    BIND("1", "已绑定卡"),

    CLOSE("2", "注销"),

    LOSE("3", "挂失");


    /**
     * 返回码
     **/
    private String code;
    /**
     * 返回信息
     **/
    private String msg;

    UsableFlagEnum(String code, String msg) {
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
    public static UsableFlagEnum getEnumBymsg(String msg) {
        for (UsableFlagEnum bookTypeEnum : UsableFlagEnum.values()) {
            if (bookTypeEnum.getMsg().equals(msg)) {
                return bookTypeEnum;
            }
        }
        return null;
    }

}
