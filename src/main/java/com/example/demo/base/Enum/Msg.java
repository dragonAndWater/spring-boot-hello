package com.example.demo.base.Enum;

public enum Msg {
    SUCCESS("000000", "请求成功"),

    ERROR("000001", "请求失败"),

    CHECK_USER_ID_VISIT_TIMES("100001", "用户达到最大访问次数限制"),

    CHECK_ATTRIBUTE_FAIL("100002", "属性检查不通过"),

    CHECK_BLACK_LIST("100003", "黑名单检查不通过"),

    CHECK_IS_ARREARAGE("100004", "欠费检查不通过"),

    CHECK_BORROW_USABLE_TIMES("100005", "剩余借阅量检查不通过"),

    CHECK_BORROW_OVERDUE("100006", "有超期书籍未归还"),

    CHECK_BORROW_BOOK("100007", "书籍未在借阅表登记"),

    CHECK_CARD("100008", "借阅卡(id)检查不通过"),

    CHECK_PHONE("100009", "电话检查不通过"),

    CHECK_NAME("100010", "名称检查不通过"),

    CHECK_CARD_("100010", "名称检查不通过"),

    PROCESSING("999999", "交易处理中");


    /**
     * 返回码
     **/
    private String code;
    /**
     * 返回信息
     **/
    private String msg;

    Msg(String code, String msg) {
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
