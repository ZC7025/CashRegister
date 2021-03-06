package com.sucheng.enums;

/**
 * 常用内容类型枚举<br />
 * 创建于2017-09-14
 *
 * @author 7025
 * @version 1.0
 */
public enum ContentTypeEnum {

    HTML("text/html; charset=UTF-8"),
    TEXT_PLAIN("text/plain; charset=UTF-8"),
    XML("text/xml; charset=UTF-8"),
    JSON("application/json; charset=UTF-8"),
    FORM_URLENCODED("application/x-www-form-urlencoded");

    private String value;

    ContentTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
