package com.scs.web.blog.service;

import com.scs.web.blog.entity.Comment;
import com.scs.web.blog.util.Result;

/**
 * @author zh_yan
 * @ClassName CommentService
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/
public interface CommentService {

    /**
     * 新增评论
     * @param commentDto
     * @return
     */
    Result addArtComments(Comment comment);

    /**
     * 根据文章id查询所有评论信息
     * @param id
     * @return
     */

}