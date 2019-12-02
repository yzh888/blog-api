package com.scs.web.blog.service;

import com.scs.web.blog.domain.dto.UserDto;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.util.Result;
import org.junit.Test;

public class UserServiceTest {
    private UserService userService = ServiceFactory.getUserServiceInstance();

    @Test
    public void signIn() {
        UserDto userDao = new UserDto();
        userDao.setMobile("13951905171");
        userDao.setPassword("111");
        Result result = userService.signIn(userDao);
        System.out.println("code:" + result.getCode() + "," + "msg:" + result.getMsg());
    }

    @Test
    public void getHotUsers() {
        Result result = userService.getHotUsers();
        System.out.println(result);
    }
}