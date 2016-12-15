package com.kobe.vote.dao;

import com.kobe.vote.entity.Answer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.CollectionUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 问题答案选项相关信息的dao
 *
 * Created by junjiewu on 16/12/15.
 */
public class AnswerDao {

    private static final String FIND_BY_QUESTION_ID = "SELECT ID, QuestionID, Content, Alias FROM V_Answer WHERE QuestionId = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Answer> findByQuestionId(int questionId) {
        List<Answer> answerList = (List<Answer>) jdbcTemplate.query(FIND_BY_QUESTION_ID, new Object[]{
                questionId
        }, new AnswerMapper());
        if (CollectionUtils.isEmpty(answerList)) {
            return null;
        }
        return answerList;
    }

    static class AnswerMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            if (rs == null) {
                return null;
            }
            Answer result = new Answer();
            result.setId(rs.getInt("ID"));
            result.setContent(rs.getString("Content"));
            result.setAlias(rs.getString("Alias"));
            result.setQuestionId(rs.getInt("QuestionID"));
            return result;
        }
    }
}
