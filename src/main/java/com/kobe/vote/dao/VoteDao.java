package com.kobe.vote.dao;

import com.kobe.vote.entity.User;
import com.kobe.vote.entity.Vote;
import com.kobe.vote.entity.VoteResult;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: kobe.wu
 * @since : 14-12-27
 */
public class VoteDao {

    private static String FIND_VOTE_RESULT = "SELECT CandidateUserID, COUNT(*) AS C FROM V_Vote WHERE PrizeID = ? GROUP BY CandidateUserID";

    private static String FIND_VOTES = "SELECT PrizeID, CandidateUserID, UserID FROM V_Vote WHERE PrizeID = ? AND UserID = ?";

    private static String FIND_HAS_VOTED_PRIZE_ID = "SELECT DISTINCT PrizeID FROM V_Vote WHERE UserID = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addVote(Vote vote) {
        jdbcTemplate.update("INSERT INTO V_Vote(PrizeID, CandidateUserID, UserID) VALUES(?, ?, ?)", new Object[] {
                vote.getPrizeId(), vote.getVotedUserId(), vote.getVoteUserId()
        });
    }

    public List<VoteResult> findVoteResult(int prizeId) {
        return (List<VoteResult>)jdbcTemplate.query(FIND_VOTE_RESULT, new Object[]{
            prizeId
        }, new VoteResultMapper());
    }

    public List<Vote> findVotes(int prizeId, int userId) {
        return (List<Vote>) jdbcTemplate.query(FIND_VOTES, new Object[]{
            prizeId, userId
        }, new VoteMapper());
    }

    public List<Integer> findHasVotedPrizeIDList(int userId) {
        return (List<Integer>) jdbcTemplate.queryForList(FIND_HAS_VOTED_PRIZE_ID, new Object[]{
            userId
        }, Integer.class);
    }

    static class VoteMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            if (rs == null) {
                return null;
            }
            Vote result = new Vote();
            result.setPrizeId(rs.getInt("PrizeID"));
            result.setVoteUserId(rs.getInt("UserID"));
            result.setVotedUserId(rs.getInt("CandidateUserID"));
            return result;
        }
    }

    static class VoteResultMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            if (rs == null) {
                return null;
            }
            VoteResult result = new VoteResult();
            result.setVotedUserId(rs.getInt("CandidateUserID"));
            result.setCount(rs.getInt("C"));
            return result;
        }
    }
}
