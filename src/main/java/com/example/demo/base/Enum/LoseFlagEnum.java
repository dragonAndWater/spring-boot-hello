package com.example.demo.base.Enum;

/**
 * @Author longtao
 * @Date 2020/10/10
 * @Describe 枚举--遗失标识  0-未遗失，1-遗失
 **/

public enum LoseFlagEnum {
    UN_LOSE("0", "未遗失"),
    LOSE("1", "遗失");

    private String code;
    private String msg;

    LoseFlagEnum(String code, String msg) {
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
    public static LoseFlagEnum getEnumBymsg(String msg) {
        for (LoseFlagEnum rareFlagEnum : LoseFlagEnum.values()) {
            if (rareFlagEnum.getMsg().equals(msg)) {
                return rareFlagEnum;
            }
        }
        return null;
    }
}
