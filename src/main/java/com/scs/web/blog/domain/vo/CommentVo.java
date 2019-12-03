package com.scs.web.blog.domain.vo;

import com.scs.web.blog.entity.User;
import lombok.Data;

import javax.xml.stream.events.Comment;

/**
 * @author zh_yan
 * @ClassName CommentVo
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/
@Data
public class CommentVo {
    private String avatar;
    private String nickname;
    private Comment comment;
    private User user;


}