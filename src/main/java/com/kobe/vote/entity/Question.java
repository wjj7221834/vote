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
    private String rightAnswer;

    /**
     * 所有答案选项列表
     */
    private List<Answer> answerList;

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

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

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}
