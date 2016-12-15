package com.kobe.vote.dao;

import com.kobe.vote.entity.Question;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.CollectionUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 问题相关信息的Dao
 *
 * Created by junjiewu on 16/12/15.
 */
public class QuestionDao {

    private static final String LOAD_QUESTION_BY_ID = "SELECT ID, Content, RightAnswer FROM V_Question WHERE ID = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Question loadById(int id) {
        List<Question> questionList = (List<Question>) jdbcTemplate.query(LOAD_QUESTION_BY_ID, new Object[]{
                id
        }, new QuestionMapper());
        if (CollectionUtils.isEmpty(questionList)) {
            return null;
        }
        return questionList.get(0);
    }

    static class QuestionMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            if (rs == null) {
                return null;
            }
            Question result = new Question();
            result.setId(rs.getInt("ID"));
            result.setContent(rs.getString("Content"));
            result.setRightAnswer(rs.getString("RightAnswer"));
            return result;
        }
    }
}
