package com.kobe.vote.entity;

/**
 * @author: kobe.wu
 * @since : 14-12-27
 */
public class StatusResponse {

    private int code;

    private StatusMessage msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public StatusMessage getMsg() {
        return msg;
    }

    public void setMsg(StatusMessage msg) {
        this.msg = msg;
    }
}
