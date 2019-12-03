package com.scs.web.blog.dao;

import com.scs.web.blog.entity.User;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.util.JSoupSpider;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class UserDaoTest {
    private UserDao userDao = DaoFactory.getUserDaoInstance();

    @Test
    public void batchInsert() throws SQLException {
        userDao.batchInsert(JSoupSpider.getUsers());
    }

    @Test
    public void findUserByMobile() throws SQLException {
        User user = userDao.findUserByMobile("13951905171");
        System.out.println(user);
    }

    @Test
    public void selectHotUsers() throws SQLException {
        List<User> userList = userDao.selectHotUsers();
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByKeywords() throws SQLException{
        List<User> userList = userDao.selectByKeywords("王");
        System.out.println(userList.size());
    }

    @Test
    public void update() throws SQLException {
        int n = userDao.update(3, 0);
        System.out.println(n);
    }

    @Test
    public void singUp() throws SQLException {
     /*   User user = new User();
        user.setMobile("12321");
        user.setPassword("123123");
        user.setNickname("哈哈");
        user.setBirthday(LocalDate.now());
        user.setCreateTime(LocalDateTime.now());
        user.setGender("nan");
       int n = userDao.singUp(user);*/

    }
}