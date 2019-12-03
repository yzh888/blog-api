package com.scs.web.blog.service;

import com.scs.web.blog.util.Result;

/**
 * @author mq_xu
 * @ClassName TopicService
 * @Description TODO
 * @Date 20:07 2019/11/16
 * @Version 1.0
 **/
public interface TopicService {
    /**
     * 获取热门专题
     * @param id
     * @return
     */
    Result getHotTopics();


    /**
     * 根据id获取专题详情
     * @param id
     * @return
     */
    Result getTopic(long id);

    /**
     * 根据名称或描述模糊搜索专题
     *
     * @param keywords
     * @return
     */
    Result selectByKeywords(String keywords);


    /**
     * 分页查询专题信息
     * @param currentPage
     * @param count
     * @return
     */
    Result selectByPage(int currentPage,int count);
    Result update(long id,int iscare);
}
