package com.kobe.vote.controller;

import com.kobe.vote.entity.Prize;
import com.kobe.vote.entity.PrizeCandidate;
import com.kobe.vote.entity.User;
import com.kobe.vote.service.PrizeCandidateService;
import com.kobe.vote.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: kobe.wu
 * @since : 14-12-27
 */
@Controller
public class PrizeController extends BaseController {

    @Autowired
    private PrizeCandidateService prizeCandidateService;

    @Autowired
    private PrizeService prizeService;

    @RequestMapping(value="/prize", method = RequestMethod.GET)
    public String prize(@RequestParam("id") int prizeId, ModelMap model, HttpServletRequest httpServletRequest) {
        User user = loadUser(httpServletRequest);
        if (user == null) {
            return "redirect:/vote/login";
        }

        List<PrizeCandidate> prizeCandidateList = prizeCandidateService.findCandidateList(prizeId);
        List<Integer> userIdList = extractUserIdList(prizeCandidateList);
        List<User> userList = userService.findUserList(userIdList);
        Prize prize = prizeService.loadPrize(prizeId);
        model.put("candidateList", userList);
        model.put("prizeId",prizeId);
        model.put("prize", prize);
        model.put("voteUserGroupId", user.getGroupId());
        return "prize";
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
