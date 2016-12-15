package com.kobe.vote.entity;

/**
 * @author: kobe.wu
 * @since : 14-12-25
 */
public class Vote {

    private int prizeId;

    // 投票人
    private int voteUserId;

    // 被投票人
    private int votedUserId;

    public int getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(int prizeId) {
        this.prizeId = prizeId;
    }

    public int getVoteUserId() {
        return voteUserId;
    }

    public void setVoteUserId(int voteUserId) {
        this.voteUserId = voteUserId;
    }

    public int getVotedUserId() {
        return votedUserId;
    }

    public void setVotedUserId(int votedUserId) {
        this.votedUserId = votedUserId;
    }
}
