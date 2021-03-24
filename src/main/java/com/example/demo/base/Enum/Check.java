package com.example.demo.base.Enum;

public enum Check {


    CHECK_PHONE("200000", "缺少手机号"),

    CHECK_NAME("200000", "缺少名称"),

    CHECK_CARD("200000", "缺少借阅卡id"),

    CHECK_CARD_NOT_EXIST("200000","借阅卡不存在"),

    CHECK_CODE_OR_NAME("200000","区域code或区域name至少上传一个"),

    ERROR("999999","失败");

    public static final String CHECK_MUST = "校验不通过: ";
    /**
     * 返回码
     **/
    private String code;
    /**
     * 返回信息
     **/
    private String msg;

    Check(String code, String msg) {
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


}
