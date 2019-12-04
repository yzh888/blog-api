package com.scs.web.blog.dao.impl;


import com.scs.web.blog.dao.CommentDao;
import com.scs.web.blog.entity.Comment;
import com.scs.web.blog.util.BeanHandler;
import com.scs.web.blog.util.DbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zh_yan
 * @ClassName CommentDaoImpl
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/
public class CommentDaoImpl implements CommentDao {
    private static Logger logger = LoggerFactory.getLogger(CommentDaoImpl.class);

    @Override
    public int insert(Comment user) throws SQLException{
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO t_comment (nickname,content,create_time) VALUES (?,?,?) ";
        PreparedStatement pst = connection.prepareStatement(sql);
        System.out.println(user);
       /* pst.setLong(1,user.getUserId());
        pst.setLong(2,user.getArticleId());*/
        pst.setString(1, user.getNickname());
        pst.setString(2, user.getContent());
        pst.setObject(3, LocalDateTime.now());

        int n = pst.executeUpdate();
        DbUtil.close(connection, pst);
        return n;
    }

    @Override
    public List<Comment> selectAll() throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_comment ORDER BY id ";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        List<Comment> commentList = BeanHandler.convertComment(rs);
        DbUtil.close(connection, pst, rs);
        return commentList;
    }

}
/*
public class CommentDaoImpl implements CommentDao {
    private static Logger logger = LoggerFactory.getLogger(CommentDaoImpl.class);
    @Override
    public int AddComments(CommentDto commentDto) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO t_comment (nickname,content,createTime) VALUES (?,?,?) ";
        PreparedStatement pst = connection.prepareStatement(sql);
        System.out.println(commentDto);
        pst.setString(1, commentDto.getNickname());
        pst.setString(2, commentDto.getContent());
        pst.setObject(3, commentDto.getCreateTime());
        System.out.println(23333);
        int n = pst.executeUpdate();
        DbUtil.close(connection, pst);
        return n;
    }
*/




