package com.scs.web.blog.service.impl;

import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.dao.CommentDao;
import com.scs.web.blog.dao.UserDao;
import com.scs.web.blog.domain.dto.CommentDto;
import com.scs.web.blog.domain.vo.CommentVo;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.service.CommentService;
import com.scs.web.blog.util.Result;
import com.scs.web.blog.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * @author zh_yan
 * @ClassName CommentServiceImpl
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/
public class CommentServiceImpl implements CommentService {
    private ArticleDao articleDao = DaoFactory.getArticleDaoInstance();
    private UserDao userDao = DaoFactory.getUserDaoInstance();
    private CommentDao commentDao = DaoFactory.getCommentDaoInstance();
    private Logger logger = LoggerFactory.getLogger(CommentService.class);
    @Override
    public int addArtComments(CommentDto commentDto) {
        int n = 0;
        try {
            n = commentDao.AddComments(commentDto);
        } catch (SQLException e) {
            logger.error("评论内容添加失败");
        }
        if(n != 0){
            try {
                n = articleDao.updateComments(commentDto.getArticleId(), commentDto.getStatus());
            } catch (SQLException e) {
                logger.error("用户评论量更新异常");
            }
            return n;
        }
        return n;
    }


}
