package com.scs.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.service.TopicService;
import com.scs.web.blog.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author mq_xu
 * @ClassName TopicController
 * @Description 专题控制器
 * @Date 2019/11/16
 * @Version 1.0
 **/
@WebServlet(urlPatterns = {"/api/topic", "/api/topic/*"})
public class TopicController extends HttpServlet {
    private TopicService topicService = ServiceFactory.getTopicServiceInstance();
    private static Logger logger = LoggerFactory.getLogger(TopicController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        if ("/api/topic".equals(uri)) {
            String page = req.getParameter("page");
            String keywords = req.getParameter("keywords");
            String count = req.getParameter("count");
            if (page != null) {
                getTopicsByPage(resp, Integer.parseInt(page), Integer.parseInt(count));
            } else if (keywords != null) {
                getTopicsByKeywords(resp, keywords);
            } else {
                getHotTopics(req, resp);
            }
        } else {
            getTopic(req, resp);
        }
    }

    private void getHotTopics(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        Result result = topicService.getHotTopics();
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getTopicsByPage(HttpServletResponse resp, int page, int count) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        Result result = topicService.selectByPage(page, count);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getTopicsByKeywords(HttpServletResponse resp, String keywords) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        Result result = topicService.selectByKeywords(keywords);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getTopic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String info = req.getPathInfo().trim();
        //取得路径参数
        String id = info.substring(info.indexOf("/") + 1);
        Gson gson = new GsonBuilder().create();
        Result result = topicService.getTopic(Long.parseLong(id));
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }
}
