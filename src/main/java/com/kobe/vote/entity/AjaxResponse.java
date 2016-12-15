package com.kobe.vote.entity;

/**
 * @author: kobe.wu
 * @since : 14-12-25
 */
public class AjaxResponse {

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
