package com.scs.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.dao.CommentDao;
import com.scs.web.blog.entity.Comment;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.util.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @author zh_yan
 * @ClassName CommentController
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/
@WebServlet(urlPatterns = {"/api/comments","/api/comments/*"})
public class CommentController extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(CommentController.class);
    private CommentDao commentDao = DaoFactory.getCommentDaoInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        if ("/api/user/sign-up".equals(uri)) {
            signUp(req, resp);

        }else{

            String id = req.getParameter("id");
            String ur = req.getRequestURI().trim();


        }
    }

    private void signUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //请求字符集设置
        req.setCharacterEncoding("UTF-8");
        //接送客户端船体的Json数据，通过缓冲字符流按行读取，存入可变长字符串中
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while((line = reader.readLine())!=null){
            stringBuilder.append(line);
        }
        System.out.println(stringBuilder.toString());
        //将接受到的客户端JSON字符串转成User对象
        Gson gson = new GsonBuilder().create();
        Comment user =gson.fromJson(stringBuilder.toString(),Comment.class);


        //插入数据库，并返回该行主键
        int id=0;
        try {
            id = DaoFactory.getCommentDaoInstance().insert(user);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //补全user的id字段信息
        user.setId((long) id);
        //通过response对象返回Json信息
        resp.setContentType("application/json;charset=utf-8");
        int code = resp.getStatus();
        String msg = code == 200 ? "成功":"失败";
        ResponseObject ro = ResponseObject.success(code,msg,user);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();
    }








}
