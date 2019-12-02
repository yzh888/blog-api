package com.scs.web.blog.domain.vo;

import com.scs.web.blog.entity.Topic;
import com.scs.web.blog.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @author mq_xu
 * @ClassName UserVo
 * @Description 用户视图类，包括用户自身信息、发表的所有文章、关注的所有专题、所有的粉丝
 * @Date 2019/11/16
 * @Version 1.0
 **/
@Data
public class UserVo {
    private User user;
    private List<ArticleVo> articleList;
    private List<Topic> topicList;
    private List<User> fansList;
}
