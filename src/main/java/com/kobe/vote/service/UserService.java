package com.kobe.vote.service;

import com.kobe.vote.dao.UserDao;
import com.kobe.vote.entity.User;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: kobe.wu
 * @since : 14-12-25
 */
public class UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public int loadAllUserCount(){
        return userDao.loadAllUserCount();
    }

    public User loadByID(int id) {
        return userDao.loadById(id);
    }

    public User loadUserByPhoneNoAndNo(String phoneNo, String no){
        // 不区分大小写
        User userWithUpperCase = userDao.loadUserByPhoneAndNo(phoneNo, no.toUpperCase());
        if (userWithUpperCase != null) {
            return userWithUpperCase;
        }
        return userDao.loadUserByPhoneAndNo(phoneNo, no.toLowerCase());
    }

    public User loadUserByNo(String no) {
        // 不区分大小写
        User userWithUpperCase = userDao.loadUserByNo(no.toUpperCase());
        if (userWithUpperCase != null) {
            return userWithUpperCase;
        }
        return userDao.loadUserByNo(no.toLowerCase());
    }

    public User loadUser(String no, String name) {
        return userDao.loadUser(no, name);
    }

    public List<User> findUserList(List<Integer> userIdList) {
        if (CollectionUtils.isEmpty(userIdList)) {
            return new ArrayList<User>();
        }
        return userDao.findUserList(userIdList);
    }
}
