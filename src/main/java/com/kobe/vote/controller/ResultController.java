package com.kobe.vote.controller;

import com.kobe.vote.entity.Prize;
import com.kobe.vote.entity.PrizeCandidate;
import com.kobe.vote.entity.User;
import com.kobe.vote.service.PrizeCandidateService;
import com.kobe.vote.service.PrizeService;
import com.kobe.vote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * @author: kobe.wu
 * @since : 14-12-25
 */
@Controller
public class ResultController {

    @Autowired
    private PrizeCandidateService prizeCandidateService;

    @Autowired
    private PrizeService prizeService;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/result", method = RequestMethod.GET)
    public String result(@RequestParam("id") int prizeId, ModelMap model) {
        List<PrizeCandidate> prizeCandidateList = prizeCandidateService.findCandidateList(prizeId);
        List<Integer> userIdList = extractUserIdList(prizeCandidateList);
        Map<Integer, User> id2User = loadUserId2Entity(userIdList);
        fill(prizeCandidateList, id2User);

        Prize prize = prizeService.loadPrize(prizeId);
        model.put("prizeCandidateList", prizeCandidateList);
        model.put("sum", loadAllUserCount()) ;
        model.put("id", prizeId);
        model.put("prize",prize);
        return "result";
    }

    private int loadAllUserCount() {
        return userService.loadAllUserCount();
    }

    private void fill(List<PrizeCandidate> results, Map<Integer, User> id2User) {
        if (CollectionUtils.isEmpty(results)) {
            return;
        }
        for (PrizeCandidate prizeCandidate : results) {
            int userId = prizeCandidate.getUserId();
            User user = id2User.get(userId);
            prizeCandidate.setName(user.getName());
            prizeCandidate.setOrderNo(user.getOrderNo());
        }
        Collections.sort(results, new Comparator<PrizeCandidate>() {
            @Override
            public int compare(PrizeCandidate o1, PrizeCandidate o2) {
                return o1.getOrderNo() - o2.getOrderNo();
            }
        });
    }

    private Map<Integer, User> loadUserId2Entity(List<Integer> userIdList) {
        List<User> userList = userService.findUserList(userIdList);
        Map<Integer, User> result = new HashMap<Integer, User>();
        for (User user : userList) {
            result.put(user.getId(), user);
        }
        return result;
    }

    private List<Integer> extractUserIdList(List<PrizeCandidate> prizeCandidateList) {
        if (CollectionUtils.isEmpty(prizeCandidateList)) {
            return new ArrayList<Integer>();
        }
        List<Integer> result = new ArrayList<Integer>();
        for (PrizeCandidate prizeCandidate : prizeCandidateList) {
            result.add(prizeCandidate.getUserId());
        }
        return result;
    }
}
