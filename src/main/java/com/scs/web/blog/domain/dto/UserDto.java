package com.scs.web.blog.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author mq_xu
 * @ClassName UserDto
 * @Description 用户传输对象
 * @Date 2019/11/9
 * @Version 1.0
 **/
@Data
public class UserDto implements Serializable {
    private String mobile;
    private String password;
    private String nickname;
    private String gender;
    private LocalDate birthday;
    private LocalDateTime createTime;
    private String code;
}
