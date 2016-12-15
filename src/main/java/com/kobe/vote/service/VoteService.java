package com.kobe.vote.service;

import com.google.common.collect.Lists;
import com.kobe.vote.dao.VoteDao;
import com.kobe.vote.entity.Vote;
import com.kobe.vote.entity.VoteResult;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author: kobe.wu
 * @since : 14-12-25
 */
public class VoteService {

    private VoteDao voteDao;

    public void setVoteDao(VoteDao voteDao) {
        this.voteDao = voteDao;
    }

    public void addVote(Vote vote) {
        voteDao.addVote(vote);
    }

    public List<VoteResult> findVoteResult(int prizeId) {
        List<VoteResult> results = voteDao.findVoteResult(prizeId);
        if (results == null) {
            return Lists.newArrayList();
        }
        return results;
    }

    public List<Vote> findVoteList(int prizeId, int userId) {
        List<Vote> voteList = voteDao.findVotes(prizeId, userId);
        if (voteList == null) {
            return Lists.newArrayList();
        }
        return voteList;
    }

    public List<Integer> findHasVotedPrizeIDList(int userId) {
        List<Integer> prizeIdList = voteDao.findHasVotedPrizeIDList(userId);
        if (prizeIdList == null) {
            return Lists.newArrayList();
        }
        return prizeIdList;
    }
}
