package com.kobe.vote.service;

import com.kobe.vote.SpringFactory;
import com.kobe.vote.entity.Question;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by junjiewu on 16/12/15.
 */
public class QuestionServiceTest {

    @Test
    @Ignore
    public void test_loadByQuestionId() {
        Question question = getQuestionService().loadQuestion(1);
        Assert.assertNotNull(question);
    }

    private QuestionService getQuestionService() {
        return (QuestionService) SpringFactory.getInstance().getBean("questionService");
    }
}
