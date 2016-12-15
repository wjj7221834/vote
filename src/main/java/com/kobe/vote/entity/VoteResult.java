package com.kobe.vote.entity;

/**
 * @author: kobe.wu
 * @since : 14-12-26
 */
public class VoteResult {

    // 被投票人
    private int votedUserId;

    private String name;

    // 投票数量
    private int count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVotedUserId() {
        return votedUserId;
    }

    public void setVotedUserId(int votedUserId) {
        this.votedUserId = votedUserId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
