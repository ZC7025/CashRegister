package com.sucheng.dos;

import java.io.Serializable;

/**
 * 所有DO的父类，具有所有DO的统一标识符id属性，id属性为Long类型
 * 创建于2017-08-15
 *
 * @author 7025
 * @version 1.0
 */
public class BaseDO implements Serializable {
    private static final long serialVersionUID = -1990701184136933177L;

    @Override
    public String toString() {
        return "BaseDO{}";
    }
}
