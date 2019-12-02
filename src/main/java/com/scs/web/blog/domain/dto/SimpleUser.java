package com.scs.web.blog.domain.dto;

import lombok.Data;

/**
 * @ClassName SimpleUser
 * @Description 简单的用户类，id、头像、昵称
 * @Author mq_xu
 * @Date 2019/11/23
 **/
@Data
public class SimpleUser {
    private Long id;
    private String nickname;
    private String avatar;
}
