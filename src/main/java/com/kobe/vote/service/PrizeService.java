package com.kobe.vote.service;

import com.google.common.collect.Lists;
import com.kobe.vote.dao.PrizeDao;
import com.kobe.vote.entity.Prize;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author: kobe.wu
 * @since : 14-12-27
 */
public class PrizeService {

    private PrizeDao prizeDao;

    public void setPrizeDao(PrizeDao prizeDao) {
        this.prizeDao = prizeDao;
    }

    public List<Prize> findNotEndedPrizeList(){
        List<Prize> prizeList = prizeDao.findNotEndedPrizeList();
        if (CollectionUtils.isEmpty(prizeList)) {
            return Lists.newArrayList();
        }
        Collections.sort(prizeList, new Comparator<Prize>() {
            @Override
            public int compare(Prize o1, Prize o2) {
                return o2.getStatusCode() - o1.getStatusCode();
            }
        });
        return prizeList;
    }

    public List<Prize> findPrizeList() {
        List<Prize> prizeList = prizeDao.findPrizeList();
        if (CollectionUtils.isEmpty(prizeList)) {
            return Lists.newArrayList();
        }
        Collections.sort(prizeList, new Comparator<Prize>() {
            @Override
            public int compare(Prize o1, Prize o2) {
                return o2.getStatusCode() - o1.getStatusCode();
            }
        });
        return prizeList;
    }

    public Prize loadPrize(int prizeId){
        return prizeDao.loadPrize(prizeId);
    }

    public void reset(int prizeId) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        Date date = calendar.getTime();
        prizeDao.updateTime(prizeId, date, date);
    }

    public void start(int prizeId) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        Date endTime = calendar.getTime();
        prizeDao.updateTime(prizeId, new Date(), endTime);
    }

    public void stop(int prizeId) {
        prizeDao.updateEndTime(prizeId, new Date());
    }

}
