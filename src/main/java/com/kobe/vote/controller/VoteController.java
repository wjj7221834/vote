package com.kobe.vote.controller;

import com.kobe.vote.entity.*;
import com.kobe.vote.service.PrizeService;
import com.kobe.vote.service.UserService;
import com.kobe.vote.service.VoteService;
import com.kobe.vote.util.CookieHelper;
import com.kobe.vote.util.NumberUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author: kobe.wu
 * @since : 14-12-25
 */
@Controller
public class VoteController extends BaseController {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private VoteService voteService;

    @Autowired
    private PrizeService prizeService;

    @RequestMapping(value="/submit", method = RequestMethod.POST)
    public void submit(@RequestParam("noToBeVoted") String noToBeVoted,@RequestParam("prizeId") int prizeId, ModelMap model, PrintWriter printWriter, HttpServletRequest httpServletRequest) {
        User user = loadUser(httpServletRequest);
        if (user == null) {
            String response = createResponse(500, "客官，你都没有登陆呢");
            flush(response, printWriter);
            return;
        }

        // 没选候选人
        if (StringUtils.isEmpty(noToBeVoted)) {
            String response = createResponse(500, "客官，你都没选人哦~");
            flush(response, printWriter);
            return;
        }

        // 奖项不存在
        Prize prize = prizeService.loadPrize(prizeId);
        if (prize == null) {
            String response = createResponse(500, "客官，你又调皮了，你的奖项不存在哦~");
            flush(response, printWriter);
            return;
        }

        // 验证投票人是否存在
        List<Integer> votedIdList = parseVotedIdList(noToBeVoted);
        List<User> userList = userService.findUserList(votedIdList);
        if (CollectionUtils.isEmpty(userList)) {
            String response = createResponse(500, "客官，你又调皮了，你投票的人都不存在哦~");
            flush(response, printWriter);
            return;
        }

        // 验证是否超过每个项目组投票的数量限制
//        Map<Integer, Integer> groupId2Count = new HashMap<Integer, Integer>();
//        for (User u : userList) {
//            int userId = u.getId();
//            if (groupId2Count.containsKey(userId)) {
//                int oldCount = groupId2Count.get(userId);
//                groupId2Count.put(userId, (oldCount + 1));
//            }
//        }
//        for (Integer userId : groupId2Count.keySet()) {
//            int count = groupId2Count.get(userId);
//            if (count > prize.getGroupMaxCount()) {
//                String response = createResponse(500, "客官，你又调皮了，该奖项每个项目组最多只能投" + prize.getGroupMaxCount() + "票哦~");
//                flush(response, printWriter);
//                return;
//            }
//        }
        int count = 0;
        for (User u : userList) {
            if (u.getGroupId() == user.getGroupId()) {
                count++;
            }
        }
        if (count > prize.getGroupMaxCount()) {
            String response = createResponse(500, "客官，你又调皮了，只能给本组的小伙伴投" + prize.getGroupMaxCount() + "票哦~");
            flush(response, printWriter);
            return;
        }

        // 验证是否达到最低投票人数的要求
        if (userList.size() < prize.getMinCount()) {
            String response = createResponse(500, "该奖项最少需要投" + prize.getMinCount() + "票哦~");
            flush(response, printWriter);
            return;
        }

        // 验证是否超过奖项最大投票次数
        if (userList.size() > prize.getMaxCandidateCount()) {
            String response = createResponse(500, "客官，你又调皮了，一个人最多只能投" + prize.getMaxCandidateCount() + "票哦~");
            flush(response, printWriter);
            return;
        }

        // 验证活动是否还没有开始
        if (prize.getStatusCode() == 0) {
            String response = createResponse(500, "客官，不要着急，活动还没开始呢~");
            flush(response, printWriter);
            return;
        }

        // 验证活动是否已经结束
        if (prize.getStatusCode() == 1) {
            String response = createResponse(500, "客官，活动已经结束喽，下次记得早点哦~");
            flush(response, printWriter);
            return;
        }

        // 验证是否已经投票过
        List<Vote> voteList = voteService.findVoteList(prizeId, user.getId());
        if (!CollectionUtils.isEmpty(voteList)) {
            String response = createResponse(500, "客官，你又调皮了，你已经投过票喽~");
            flush(response, printWriter);
            return;
        }

        // do add
        for (Integer votedUserId : votedIdList) {
            Vote vote = new Vote();
            vote.setPrizeId(prizeId);
            vote.setVotedUserId(votedUserId);
            vote.setVoteUserId(user.getId());
            voteService.addVote(vote);
        }

        String response = createResponse(200, "投票成功");
        flush(response, printWriter);
    }

    private List<Integer> parseVotedIdList(String noToBeVoted) {
        List<Integer> result = new ArrayList<Integer>();
        String[] nos = noToBeVoted.split(",");
        if (nos.length == 0) {
            return result;
        }
        for (String no : nos) {
            int intValue = parseIntQuitely(no);
            if (intValue == -1) {
                continue;
            }
            result.add(intValue);
        }
        return result;
    }

    private int parseIntQuitely(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return -1;
        }
    }


    private void flush(String msg, PrintWriter pw) {
        pw.print(msg);
        pw.flush();
        pw.close();
    }

    private String createResponse(int code, String msg) {
        AjaxResponse result = new AjaxResponse();
        result.setCode(code);
        result.setMsg(msg);

        try {
            return objectMapper.writeValueAsString(result);
        } catch (Exception e) {
            return "";
        }
    }

}
