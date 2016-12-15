package com.kobe.vote.controller;

import com.kobe.vote.entity.User;
import com.kobe.vote.service.UserService;
import com.kobe.vote.util.CookieHelper;
import com.kobe.vote.util.NumberUtils;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * @author: kobe.wu
 * @since : 14-12-27
 */
@Controller
public class LoginController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(ModelMap model, HttpServletRequest request) {
        String errorParam = request.getParameter("code");
        model.put("code", parseIntQuietly(errorParam));
        return "login";
    }

    private int parseIntQuietly(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return -1;
        }
    }

    @RequestMapping(value="/login/post", method = RequestMethod.POST)
    public String login(@RequestParam("phoneNo") String phoneNo,
                        @RequestParam("pass") String pass, ModelMap model, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Cache-Control", "no-cache");
        User user = userService.loadUserByPhoneNoAndNo(phoneNo, pass);
        if (user == null) {
            return "redirect:/vote/login?code=403";
        }

        String cookieOriginValue = "j_" + user.getId();
        String cookieValueEncrpted = new String(Base64.encodeBase64(cookieOriginValue.getBytes()));

        Cookie cookie = new Cookie("l",cookieValueEncrpted);
        cookie.setPath("/");
        cookie.setMaxAge(10000000);
        httpServletResponse.addCookie(cookie);
        return "redirect:/vote/index";
    }
}
