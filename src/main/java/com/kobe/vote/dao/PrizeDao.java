package com.kobe.vote.dao;

import com.kobe.vote.entity.Prize;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author: kobe.wu
 * @since : 14-12-27
 */
public class PrizeDao {

    private static String QUERY_PRIZE_LIST = "SELECT ID, Name, MaxUser,MinCount, GrouponMaxUser, BeginTime, EndTime FROM V_Prize";

    private static String FIND_NOT_ENDED_PRIZE_LIST = "SELECT ID, Name, MaxUser, MinCount, GrouponMaxUser, BeginTime, EndTime FROM V_Prize WHERE EndTime > NOW()";

    private static String LOAD_PRIZE = "SELECT ID, Name, MaxUser, MinCount, GrouponMaxUser, BeginTime, EndTime FROM V_Prize WHERE ID = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Prize> findNotEndedPrizeList(){
        return  (List<Prize>) jdbcTemplate.query(FIND_NOT_ENDED_PRIZE_LIST, new PrizeMapper());
    }

    public List<Prize> findPrizeList(){
        return  (List<Prize>) jdbcTemplate.query(QUERY_PRIZE_LIST, new PrizeMapper());
    }

    public Prize loadPrize(int prizeId) {
        List<Prize> prizeList = jdbcTemplate.query(LOAD_PRIZE, new Object[]{
            prizeId
        }, new PrizeMapper());
        if (CollectionUtils.isEmpty(prizeList)) {
            return null;
        }
        return prizeList.get(0);
    }

    public void updateTime(int prizeId, Date beginTime, Date endTime) {
        jdbcTemplate.update("UPDATE V_Prize SET BeginTime = ?, EndTime = ? WHERE ID = ?", new Object[]{
                beginTime, endTime, prizeId
        });
    }

    public void updateEndTime(int prizeId, Date endTime) {
        jdbcTemplate.update("UPDATE V_Prize SET EndTime = ? WHERE ID = ?", new Object[]{
                endTime, prizeId
        });
    }

    static class PrizeMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            if (rs == null) {
                return null;
            }
            Prize result = new Prize();
            result.setId(rs.getInt("ID"));
            result.setMaxCandidateCount(rs.getInt("MaxUser"));
            result.setGroupMaxCount(rs.getInt("GrouponMaxUser"));
            result.setName(rs.getString("Name"));
            result.setMinCount(rs.getInt("MinCount"));
            result.setStartTime(rs.getDate("BeginTime"));
            result.setEndTime(rs.getDate("EndTime"));
            return result;
        }
    }
}
