package com.kobe.vote.dao;

import com.kobe.vote.entity.PrizeCandidate;
import com.kobe.vote.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.CollectionUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: kobe.wu
 * @since : 14-12-27
 */
public class PrizeCandidateDao {

    private static String FIND_PRIZE_CANDIDATE = "SELECT ID, PrizeID, UserID FROM V_PrizeCandidate WHERE PrizeID = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PrizeCandidate> findPrizeCandidate(int prizeId) {
        return  (List<PrizeCandidate>) jdbcTemplate.query(FIND_PRIZE_CANDIDATE, new Object[]{
                prizeId
        }, new PrizeCandidateMapper());
    }


    static class PrizeCandidateMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            if (rs == null) {
                return null;
            }
            PrizeCandidate result = new PrizeCandidate();
            result.setId(rs.getInt("ID"));
            result.setPrizeId(rs.getInt("PrizeID"));
            result.setUserId(rs.getInt("UserID"));
            return result;
        }
    }
}
