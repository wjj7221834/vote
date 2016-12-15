package com.kobe.vote.dao;

import com.kobe.vote.SpringFactory;
import com.kobe.vote.entity.User;
import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author: kobe.wu
 * @since : 14-12-27
 */
public class UserDaoTest {

    @Test
    @Ignore
    public void test_loadUser() {
        User user = getUserDao().loadUser("296817", "吴俊杰1");
        Assert.assertNull(user);
    }

    private UserDao getUserDao() {
        return (UserDao) SpringFactory.getInstance().getBean("userDao");
    }
}
