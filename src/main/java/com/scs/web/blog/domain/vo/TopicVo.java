package com.scs.web.blog.domain.vo;

import com.scs.web.blog.entity.Topic;
import com.scs.web.blog.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @author mq_xu
 * @ClassName TopicVo
 * @Description 专题视图类, 包含专题自身信息、管理员信息、专题下所有文章、关注者列表
 * @Date 2019/11/16
 * @Version 1.0
 **/
@Data
public class TopicVo {
    private Topic topic;
    private User admin;
    private List<ArticleVo> articleList;
    private List<User> followList;
}
