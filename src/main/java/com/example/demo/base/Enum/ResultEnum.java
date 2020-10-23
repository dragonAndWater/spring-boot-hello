package com.example.demo.base.Enum;

public enum ResultEnum {
    SUCCESS("000000", "交易成功"),

    PROCESSING("999999", "交易处理中"),

    FAIL("000001", "交易失败"),

    CHECK_USER_ID_VISIT_TIMES("000002","用户达到最大访问次数限制"),

    CHECK_ATTRIBUTE_FAIL("000003","属性检查失败：");


    /**
     * 返回码
     **/
    private String code;
    /**
     * 返回信息
     **/
    private String msg;

    ResultEnum(String code, String msg) {
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
