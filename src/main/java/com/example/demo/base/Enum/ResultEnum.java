package com.example.demo.base.Enum;

public enum ResultEnum {
    SUCCESS("000000", "交易成功"),

    FAIL("000001", "交易失败"),

    CHECK_USER_ID_VISIT_TIMES("100001", "用户达到最大访问次数限制"),

    CHECK_ATTRIBUTE_FAIL("100002", "属性检查不通过"),

    CHECK_BLACK_LIST("100003", "黑名单检查不通过"),

    CHECK_IS_ARREARAGE("100004", "欠费检查不通过"),

    CHECK_BORROW_USABLE_TIMES("100005", "剩余借阅量检查不通过"),

    CHECK_BORROW_OVERDUE("100006", "有超期书籍未归还"),

    PROCESSING("999999", "交易处理中");


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
