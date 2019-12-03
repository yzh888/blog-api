package com.scs.web.blog.dao.impl;


import com.scs.web.blog.dao.CommentDao;
import com.scs.web.blog.domain.dto.CommentDto;
import com.scs.web.blog.domain.vo.CommentVo;
import com.scs.web.blog.util.DbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author zh_yan
 * @ClassName CommentDaoImpl
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/
/*
public class CommentDaoImpl implements CommentDao {
    private static Logger logger = LoggerFactory.getLogger(CommentDaoImpl.class);

    @Override
    public int insert(Comment user) throws SQLException{
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO t_comment (nickname,content,createTime) VALUES (?,?,?) ";
        PreparedStatement pst = connection.prepareStatement(sql);
        System.out.println(user);
        pst.setString(1, user.getNickname());
        pst.setString(2, user.getContent());
        pst.setObject(3, user.getCreateTime());

        int n = pst.executeUpdate();
        DbUtil.close(connection, pst);
        return n;
    }
}*/
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

        int n = pst.executeUpdate();
        DbUtil.close(connection, pst);
        return n;
    }



    @Override
    public List<CommentVo> getCommentByUserId(long userId) throws SQLException {
        Connection connection = DbUtil.getConnection();
        return null;
    }
}