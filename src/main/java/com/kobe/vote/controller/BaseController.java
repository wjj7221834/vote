package com.kobe.vote.controller;

import com.kobe.vote.entity.User;
import com.kobe.vote.service.UserService;
import com.kobe.vote.util.CookieHelper;
import com.kobe.vote.util.NumberUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: kobe.wu
 * @since : 15-1-10
 */
public class BaseController {

    @Autowired
    protected UserService userService;

    protected User loadUser(HttpServletRequest request){
        String valueDecoded = CookieHelper.getCookieValue(request, "l");
        if (valueDecoded == null) {
            return null;
        }
        String value = new String(Base64.decodeBase64(valueDecoded.getBytes()));
        String[] fields = value.split("_");
        if (fields.length != 2 || NumberUtils.parseQuietly(fields[1]) == -1) {
            return null;
        }
        int userId = NumberUtils.parseQuietly(fields[1]);
        return userService.loadByID(userId);
    }
}
