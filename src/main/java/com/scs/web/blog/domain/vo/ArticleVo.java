package com.scs.web.blog.domain.vo;

import com.scs.web.blog.entity.Article;
import com.scs.web.blog.entity.Topic;
import com.scs.web.blog.entity.User;
import lombok.Data;

/**
 * @author mq_xu
 * @ClassName ArticleVo
 * @Description 文章视图类, 从文章、专题、用户表联查出前端需要展示的数据
 * @Date 2019/11/11
 * @Version 1.0
 **/
@Data
public class ArticleVo {
    private Article article;
    private User author;
    private Topic topic;
}
