package com.sucheng.vo;

import com.sucheng.enums.ControllerStatusEnum;

/**
 * 控制器结果，通常都是在页面上执行添加，删除，状态改变等操作时，由控制器返回执行结果的JSON格式数据到前端页面
 * 创建于2017-08-16
 *
 * @author 7025
 * @version 1.0
 * @see ControllerStatusEnum
 */
public class ControllerStatusVO extends BaseVO {

    private static final long serialVersionUID = 4997020566681368159L;

    private static final String STATUS_OK = "ok";
    private static final String STATUS_ERROR = "error";
    private static final String STATUS_DATA_ERROR = "data-error";

    private Integer code;
    private String status;
    private String message;

    public ControllerStatusVO() {}

    public ControllerStatusVO(Integer code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取表示执行成功的结果
     *
     * @param code 状态码
     * @param message 需要返回到前端页面的提示信息
     */
    public void okStatus(Integer code, String message) {
        setCode(code);
        setStatus(STATUS_OK);
        setMessage(message);
    }

    /**
     * 获取表示执行失败的结果
     *
     * @param code 状态码
     * @param message 需要返回到前端页面的提示信息
     */
    public void errorStatus(Integer code, String message) {
        setCode(code);
        setStatus(STATUS_ERROR);
        setMessage(message);
    }

    /**
     * 获取表示数据错误的结果
     *
     * @param code 状态码
     * @param message 需要返回到前端页面的提示信息
     * @return 表示数据错误的结果
     */
    public void dataErrorStatus(Integer code, String message) {
        setCode(code);
        setStatus(STATUS_DATA_ERROR);
        setMessage(message);
    }
    @Override
    public String toString() {
        return "ControllerResultVO{" +
                "code=" + code +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
