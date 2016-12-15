package com.kobe.vote.entity;

/**
 * 答案
 *
 * Created by junjiewu on 16/12/15.
 */
public class Answer {

    /**
     * 答案的唯一标识
     */
    private int id;

    /**
     * 问题对应的标识
     */
    private int questionId;

    /**
     * 别名(例如:A B C)
     */
    private String alias;

    /**
     * 答案内容
     */
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
