package com.kobe.vote.dao;

import com.google.common.base.Joiner;
import com.kobe.vote.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.CollectionUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: kobe.wu
 * @since : 14-12-27
 */
public class UserDao {

    private static String LOAD_USER =  "SELECT ID, UserName, IndentifyNo, OrderNo, PhoneNo, GroupID, GroupName FROM V_User WHERE IndentifyNo = ? AND UserName = ?";

    private static String FIND_USER_LIST = "SELECT ID, UserName, IndentifyNo, OrderNo, PhoneNo, GroupID, GroupName FROM V_User WHERE ID IN (%s) ORDER BY OrderNo ASC";

    private static String LOAD_USER_BY_NO = "SELECT ID, UserName, IndentifyNo, OrderNo, PhoneNo, GroupID, GroupName FROM V_User WHERE IndentifyNo = ?";

    private static String LOAD_USER_BY_ID = "SELECT ID, UserName, IndentifyNo, OrderNo, PhoneNo, GroupID, GroupName FROM V_User WHERE ID = ?";

    private static String LOAD_USER_BY_PHONE_AND_NO = "SELECT ID, UserName, IndentifyNo, OrderNo, PhoneNo, GroupID, GroupName FROM V_User WHERE PhoneNo = ? AND IndentifyNo = ?";

    private static String LOAD_ALL_USER_COUNT = "SELECT COUNT(*) FROM V_User";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int loadAllUserCount(){
        return jdbcTemplate.queryForInt(LOAD_ALL_USER_COUNT);
    }

    public User loadUser(String no, String name) {
        List<User> userList = (List<User>) jdbcTemplate.query(LOAD_USER, new Object[]{
                no, name
        }, new UserMapper());
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        return userList.get(0);
    }

    public List<User> findUserList(List<Integer> userIdList) {
        String sql = String.format(FIND_USER_LIST, Joiner.on(',').join(userIdList));
        return (List<User>) jdbcTemplate.query(sql, new UserMapper());
    }

    public User loadUserByNo(String no) {
        List<User> userList = (List<User>) jdbcTemplate.query(LOAD_USER_BY_NO, new Object[]{
                no
        }, new UserMapper());
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        return userList.get(0);
    }

    public User loadUserByPhoneAndNo(String phoneNo, String no) {
        List<User> userList = (List<User>) jdbcTemplate.query(LOAD_USER_BY_PHONE_AND_NO, new Object[]{
                phoneNo, no
        }, new UserMapper());
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        return userList.get(0);
    }

    public User loadById(int id) {
        List<User> userList = (List<User>) jdbcTemplate.query(LOAD_USER_BY_ID, new Object[]{
                id
        }, new UserMapper());
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        return userList.get(0);
    }

    static class UserMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            if (rs == null) {
                return null;
            }
            User result = new User();
            result.setId(rs.getInt("ID"));
            result.setNo(rs.getString("IndentifyNo"));
            result.setName(rs.getString("UserName"));
            result.setOrderNo(rs.getInt("OrderNo"));
            result.setPhoneNo(rs.getString("PhoneNo"));
            result.setGroupId(rs.getInt("GroupID"));
            result.setGroupName(rs.getString("GroupName"));
            return result;
        }
    }

}
