package com.kobe.vote.controller;

import com.kobe.vote.entity.Prize;
import com.kobe.vote.entity.User;
import com.kobe.vote.service.PrizeService;
import com.kobe.vote.service.UserService;
import com.kobe.vote.service.VoteService;
import com.kobe.vote.util.CookieHelper;
import com.kobe.vote.util.NumberUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: kobe.wu
 * @since : 14-12-25
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private PrizeService prizeService;

    @Autowired
    private VoteService voteService;

    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest httpServletRequest) {
        User user = loadUser(httpServletRequest);
        if (user == null) {
            return "redirect:/vote/login";
        }
        List<Prize> prizeList = prizeService.findNotEndedPrizeList();
        fillHasVotedInfo(prizeList, user.getId());
        model.put("userName", user.getName());
        model.put("prizeList", prizeList != null ? prizeList : new ArrayList<Prize>());
        return "index";
    }

    private void fillHasVotedInfo(List<Prize> prizeList, int id) {
        List<Integer> hasVotedPrizeIdList = voteService.findHasVotedPrizeIDList(id);
        Set<Integer> uniqPrizeIdList = new HashSet<Integer>(hasVotedPrizeIdList);
        for (Prize prize : prizeList) {
            if (uniqPrizeIdList.contains(prize.getId())) {
                prize.setHasVoted(true);
            }
        }
    }
}
