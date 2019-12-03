package com.scs.web.blog.dao;

import com.scs.web.blog.entity.Comment;

import java.sql.SQLException;

/**
 * @author zh_yan
 * @ClassName Comment
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/
public interface CommentDao {
      int insert(Comment user) throws SQLException;

}