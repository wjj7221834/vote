package com.kobe.vote.service;

import com.kobe.vote.dao.AnswerDao;
import com.kobe.vote.dao.QuestionDao;
import com.kobe.vote.entity.Answer;
import com.kobe.vote.entity.Question;
import com.kobe.vote.util.NumberUtils;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 试题相关的服务接口
 * 1.获取某个问题的详细信息(问题内容 + 答案选项列表)
 * 2.问题的答案是否准确
 *
 * Created by junjiewu on 16/12/15.
 */
public class QuestionService {

    private QuestionDao questionDao;

    private AnswerDao answerDao;

    public void setAnswerDao(AnswerDao answerDao) {
        this.answerDao = answerDao;
    }

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    /**
     * 获取某个问题的信息(问题 + 答案选项列表)
     *
     * @param questionId
     * @return
     */
    public Question loadQuestion(int questionId) {
        Assert.isTrue(questionId > 0,  "questionId should larger than 0");
        Question question = questionDao.loadById(questionId);
        if (question == null) {
            return null;
        }
        List<Answer> answerList = answerDao.findByQuestionId(questionId);
        question.setAnswerList(answerList);
        return question;
    }

    /**
     * 答案是否准确
     *
     * @param questionId
     * @param answerId
     * @return
     */
    public boolean isAnswerRight(int questionId, int answerId) {
        Question question = loadQuestion(questionId);
        if (question == null) {
            return false;
        }
        List<Integer> rightAnswerIdList = NumberUtils.toIntList(question.getRightAnswer());
        return rightAnswerIdList.contains(answerId);
    }
}
