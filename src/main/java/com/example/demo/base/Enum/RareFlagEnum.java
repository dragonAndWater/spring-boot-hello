package com.example.demo.base.Enum;

/**
 * @Author longtao
 * @Date 2020/10/10
 * @Describe 枚举--珍本标识
 **/

public enum RareFlagEnum {
    UNRARE_FLAG("unra", "非珍本"),
    RARE_FLAG("rare", "珍本");

    private String code;
    private String msg;

    RareFlagEnum(String code, String msg) {
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
    public static RareFlagEnum getEnumBymsg(String msg) {
        for (RareFlagEnum rareFlagEnum : RareFlagEnum.values()) {
            if (rareFlagEnum.getMsg().equals(msg)) {
                return rareFlagEnum;
            }
        }
        return null;
    }
}
