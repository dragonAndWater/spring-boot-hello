package com.example.demo.base.Enum;

/**
 * @Author longtao
 * @Date 2020/10/10
 * @Describe 枚举--书籍类别
 **/
public enum BookTypeEnum {
    KID_BOOK("1", "童书"),

    LITERATURE_BOOK("2", "文学"),

    REFERENCE_BOOK("3", "教辅"),

    ECONOMICS_BOOK("4", "经管投资"),

    SOCIAL_BOOK("5", "社科"),

    TECHNOLOGY_BOOK("6", "科技"),

    LIFE_BOOK("7", "生活育儿"),

    ART_BOOK("8", "艺术"),

    CARTOON_BOOK("9", "动漫"),

    OTHER_BOOK("10", "其他");


    /**
     * 返回码
     **/
    private String code;
    /**
     * 返回信息
     **/
    private String msg;

    BookTypeEnum(String code, String msg) {
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
    public static BookTypeEnum getEnumBymsg(String msg) {
        for (BookTypeEnum bookTypeEnum : BookTypeEnum.values()) {
            if (bookTypeEnum.getMsg().equals(msg)) {
                return bookTypeEnum;
            }
        }
        return null;
    }

}
