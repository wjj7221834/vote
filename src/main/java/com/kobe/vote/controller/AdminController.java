package com.kobe.vote.controller;

import com.kobe.vote.entity.AjaxResponse;
import com.kobe.vote.entity.Prize;
import com.kobe.vote.service.PrizeService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: kobe.wu
 * @since : 14-12-25
 */
@Controller
public class AdminController {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private PrizeService prizeService;

    @RequestMapping(value="/admin_is_jean", method = RequestMethod.GET)
    public String index(ModelMap model) {
        List<Prize> prizeList = prizeService.findPrizeList();
        model.put("prizeList", prizeList != null ? prizeList : new ArrayList<Prize>());
        return "admin";
    }

    @RequestMapping(value="/admin/setstatus", method = RequestMethod.GET)
    public void setStatus(@RequestParam("id") int prizeId, @RequestParam("status") int status,
                          ModelMap model, PrintWriter pw) {

        // 0 : 初始化；1：开始；2：结束
        if (status < 0 || status > 2) {
            String response = createResponse(500, "状态码不合理~");
            flush(response, pw);
            return;
        }

        Prize prize = prizeService.loadPrize(prizeId);
        if (prize == null) {
            String response = createResponse(500, "活动不存在~");
            flush(response, pw);
            return;
        }

        // 将开始时间和结束时间设置成1年以后
        if (status == 0) {
            prizeService.reset(prizeId);
        } else if (status == 1) {
            // 将开始时间设置成当前时间
            prizeService.start(prizeId);
        } else {
            // 将结束时间设置成当前时间
            prizeService.stop(prizeId);
        }
        String response = createResponse(200, "操作成功~");
        flush(response, pw);
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
