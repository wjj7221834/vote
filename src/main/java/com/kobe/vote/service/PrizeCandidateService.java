package com.kobe.vote.service;

import com.kobe.vote.dao.PrizeCandidateDao;
import com.kobe.vote.entity.PrizeCandidate;

import java.util.List;

/**
 * @author: kobe.wu
 * @since : 14-12-27
 */
public class PrizeCandidateService {

    private PrizeCandidateDao prizeCandidateDao;

    public void setPrizeCandidateDao(PrizeCandidateDao prizeCandidateDao) {
        this.prizeCandidateDao = prizeCandidateDao;
    }

    public List<PrizeCandidate> findCandidateList(int prizeId) {
        return prizeCandidateDao.findPrizeCandidate(prizeId);
    }
}
