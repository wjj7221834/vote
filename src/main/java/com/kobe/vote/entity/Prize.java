package com.kobe.vote.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: kobe.wu
 * @since : 14-12-27
 */
public class Prize {

    private int id;

    private String name;

    private int maxCandidateCount;

    private int groupMaxCount;

    // 最少投票次数
    private int minCount;

    private Date startTime;

    private Date endTime;

    // 是否已经投票
    private boolean hasVoted = false;

    private static Map<Integer, String> code2Msg = new HashMap<Integer, String>();

    static {
        code2Msg.put(0, "未开始");
        code2Msg.put(1, "已结束");
        code2Msg.put(2, "进行中");
    }

    /**
     * status = 0 : 未开始
     * status = 1 : 已结束
     * status = 2 : 进行中
     */
    public String getStatus() {
        int statusCode = getStatusCode();
        return code2Msg.get(statusCode);
    }

    public boolean isHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    public int getStatusCode() {
        Date now = new Date();
        // 未开始
        if (now.before(startTime)) {
            return 0;
        } else if (now.after(endTime)) {
            return 1;
        } else {
            return 2;
        }
    }

    public int getMinCount() {
        return minCount;
    }

    public void setMinCount(int minCount) {
        this.minCount = minCount;
    }

    public int getGroupMaxCount() {
        return groupMaxCount;
    }

    public void setGroupMaxCount(int groupMaxCount) {
        this.groupMaxCount = groupMaxCount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxCandidateCount() {
        return maxCandidateCount;
    }

    public void setMaxCandidateCount(int maxCandidateCount) {
        this.maxCandidateCount = maxCandidateCount;
    }
}
