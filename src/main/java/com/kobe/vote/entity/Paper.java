package com.kobe.vote.entity;

import java.util.List;

/**
 * 试卷
 *
 * Created by junjiewu on 16/12/15.
 */
public class Paper {

    /**
     * 试题列表
     */
    private List<Integer> questionIdList;

    public List<Integer> getQuestionIdList() {
        return questionIdList;
    }

    public void setQuestionIdList(List<Integer> questionIdList) {
        this.questionIdList = questionIdList;
    }
}
