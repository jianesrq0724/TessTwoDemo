package com.carl.mvpdemo.pub.network.exception;

/**
 * @author Carl
 * version 1.0
 * @since 2018/6/11
 */
public class UserException extends CommonException {
    protected String code;
    protected String msg;

    public UserException(String code, String msg) {
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
