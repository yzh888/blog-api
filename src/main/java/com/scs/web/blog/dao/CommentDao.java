package com.scs.web.blog.dao;

import com.scs.web.blog.domain.dto.CommentDto;
import com.scs.web.blog.domain.vo.CommentVo;

import java.sql.SQLException;
import java.util.List;

/**
 * @author zh_yan
 * @ClassName Comment
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/
/*
public interface CommentDao {
      int insert(Comment user) throws SQLException;

}*/
public interface CommentDao {
      /**
       * 添加新的评论
       * @param commentDto
       * @return
       * @throws SQLException
       */
      int AddComments (CommentDto commentDto) throws SQLException;

      /**
       * 根据文章id查询该文章的评论
       * @param articleId
       * @return
       * @throws SQLException
       */


      /**
       * 根据用户id得到评论信息
       * @param userId
       * @return
       * @throws SQLException
       */
      List<CommentVo> getCommentByUserId(long userId) throws SQLException;
}