package com.kobe.vote.controller;

import com.kobe.vote.entity.*;
import com.kobe.vote.service.VoteService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: kobe.wu
 * @since : 14-12-27
 */
@Controller
public class StatusController {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private VoteService voteService;

    @RequestMapping(value="/status", method = RequestMethod.GET)
    public void result(@RequestParam("id") int prizeId, ModelMap model,PrintWriter printWriter) {
        List<VoteResult> results = voteService.findVoteResult(prizeId);
        String response = createResponse(results);
        flush(response, printWriter);
    }

    private void flush(String msg, PrintWriter pw) {
        pw.print(msg);
        pw.flush();
        pw.close();
    }

    private String createResponse(List<VoteResult> voteResultList) {
        StatusMessage statusMessage = new StatusMessage();
        statusMessage.setStatusCode(1);
        statusMessage.setUserId2Count(convert2Map(voteResultList));

        StatusResponse result = new StatusResponse();
        result.setCode(200);
        result.setMsg(statusMessage);

        try {
            return objectMapper.writeValueAsString(result);
        } catch (Exception e) {
            return "";
        }
    }

    private Map<Integer, Integer> convert2Map(List<VoteResult> voteResultList) {
        Map<Integer, Integer> result = new HashMap<Integer, Integer>();
        for (VoteResult voteResult : voteResultList) {
            result.put(voteResult.getVotedUserId(), voteResult.getCount());
        }
        return result;
    }
}
