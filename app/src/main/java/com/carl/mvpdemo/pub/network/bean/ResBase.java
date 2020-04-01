package com.carl.mvpdemo.pub.network.bean;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * @author Carl
 * version 1.0
 * @since 2018/6/11
 */
public class ResBase implements Serializable {

    public ResBase() {
    }

    public ResBase(String msg) {
        this.msg = msg;
    }

    /**
     * code : 401
     * msg : 验证码过期
     */

    public String code;
    public String Code;

    public String success;
    public String Success;

    public String Status;

    public String status;

    public String state;

    public String tag;

    public String errcode;

    public String errmsg;

    public String msg = "";
    public String MSG = "";
    public String Message = "";
    public String message = "";

    public String token;

    public boolean isSuccess;

    public boolean IsSuccess;

    public String retCode;

    public String url;

    public String detail;

    public String imgsrc;

    public String Result;

    public String ErrorMsg;


    public String getTipMsg() {
        String tipMsg = "";
        if (!TextUtils.isEmpty(msg)) {
            tipMsg = msg;
        } else if (!TextUtils.isEmpty(MSG)) {
            tipMsg = MSG;
        } else if (!TextUtils.isEmpty(Message)) {
            tipMsg = Message;
        } else if (!TextUtils.isEmpty(message)) {
            tipMsg = message;
        }else if (!TextUtils.isEmpty(ErrorMsg)) {
            tipMsg = ErrorMsg;
        }
        return tipMsg;
    }


    @Override
    public String toString() {
        return "ResBase{" +
                "code='" + code + '\'' +
                ", status='" + status + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", msg='" + getTipMsg() + '\'' +
                '}';
    }
}
