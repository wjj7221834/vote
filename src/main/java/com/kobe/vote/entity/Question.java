package com.kobe.vote.entity;

import java.util.List;

/**
 * 试题
 *
 * Created by junjiewu on 16/12/15.
 */
public class Question {

    /**
     * 试题标识
     */
    private int id;

    /**
     * 试题内容
     */
    private String content;

    /**
     * 正确答案标识
     */
    private int rightAnswerId;

    /**
     * 所有答案选项列表
     */
    private List<Integer> answerIdList;

    /**
     * 是否是多选题
     */
    private boolean hasMultiAnswer;

    /**
     * 试题状态(0:不可答题;1:可以答题)
     */
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRightAnswerId() {
        return rightAnswerId;
    }

    public void setRightAnswerId(int rightAnswerId) {
        this.rightAnswerId = rightAnswerId;
    }

    public List<Integer> getAnswerIdList() {
        return answerIdList;
    }

    public void setAnswerIdList(List<Integer> answerIdList) {
        this.answerIdList = answerIdList;
    }

    public boolean isHasMultiAnswer() {
        return hasMultiAnswer;
    }

    public void setHasMultiAnswer(boolean hasMultiAnswer) {
        this.hasMultiAnswer = hasMultiAnswer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
