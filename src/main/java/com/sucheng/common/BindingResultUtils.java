package com.sucheng.common;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * BindingResult工具类<br/>
 *
 * 创建于2018-04-16
 *
 * @author 7025
 * @version 1.0
 */
public class BindingResultUtils {

    /**
     * 从BindingResult中获取所有fieldError，并组装成一个长字符串
     * @param bindingResult BindingResult实例
     * @return 所有字段错误信息的长字符串
     */
    public static String errorString(BindingResult bindingResult) {
        StringBuilder errorString = new StringBuilder();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrorList) {
            errorString.append(fieldError.getField())
                    .append(": ")
                    .append(fieldError.getDefaultMessage())
                    .append("<br/>");
        }
        return errorString.toString();
    }

}
