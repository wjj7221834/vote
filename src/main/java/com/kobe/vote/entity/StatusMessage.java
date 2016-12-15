package com.kobe.vote.entity;

import java.util.Map;

/**
 * @author: kobe.wu
 * @since : 14-12-27
 */
public class StatusMessage {

    private Map<Integer, Integer> userId2Count;

    private int statusCode;

    public Map<Integer, Integer> getUserId2Count() {
        return userId2Count;
    }

    public void setUserId2Count(Map<Integer, Integer> userId2Count) {
        this.userId2Count = userId2Count;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
