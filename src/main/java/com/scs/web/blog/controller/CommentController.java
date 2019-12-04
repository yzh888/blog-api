package com.scs.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.dao.CommentDao;
import com.scs.web.blog.entity.Comment;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.service.CommentService;
import com.scs.web.blog.util.Result;
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
import java.util.List;

/**
 * @author zh_yan
 * @ClassName CommentController
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/

@WebServlet(urlPatterns = {"/api/comment","/api/comment/*"})
public class CommentController extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(CommentController.class);
    private CommentDao commentDao = DaoFactory.getCommentDaoInstance();
    private CommentService commentService = ServiceFactory.getCommentServiceInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   /*     String uri = req.getRequestURI().trim();
        if ("/comment".equals(uri)) {
            getComment(req, resp);

        }
    }*/
        CommentService commentService = ServiceFactory.getCommentServiceInstance();
        List<Comment> comments = commentService.listComment();
        Gson gson = new Gson();
        resp.setContentType("application/json;charset='utf-8'");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(comments));
        out.close();
    }

   /* private void getComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        System.out.println(user);


        Result rs = commentService.getComment(user);
        //补全user的id字段信息
        //通过response对象返回Json信息
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(rs));
        out.close();
    }
*/


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        if ("/api/comment/con".equals(uri)) {
            Connect(req, resp);

        }
    }

    private void Connect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
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

        System.out.println(user);

        //插入数据库，并返回该行主键
        Result rs = commentService.addArtComments(user);
        //补全user的id字段信息
        //通过response对象返回Json信息
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(rs));
        out.close();
    }








}

/*
@WebServlet(urlPatterns ={"/comment", "/comment/*"})
public class CommentController extends HttpServlet {
    private CommentService commentService = ServiceFactory.getCommentServiceInstance();
    private Logger logger = LoggerFactory.getLogger(CommentController.class);
    private CommentDao commentDao= DaoFactory.getCommentDaoInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI().trim();
        if(url.equals("/comment")){
            System.out.println("123");
        }else {
            System.out.println("12334");
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI().trim();
        System.out.println(url);
        if(url.equals("/comment")){
            addComments(req, resp);
        }
    }

    private void addComments(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String line = null;
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null){
            stringBuilder.append(line);
        }
       logger.info("添加的评论信息:" + stringBuilder);

        Gson gson = new GsonBuilder().create();
        CommentDto cdo = gson.fromJson(stringBuilder.toString(), CommentDto.class);
        int n = commentService.addArtComments(cdo);
        ResponseObject ro = new ResponseObject();
        ro.setCode(res.getStatus());
        if(res.getStatus() == 200){
            ro.setMsg("成功");
        }else {
            ro.setMsg("失败");
        }
        ro.setData(n);

        */
/*int code = resp.getStatus();
        String msg = code == 200 ? "成功":"失败";
        ResponseObject ro = ResponseObject.success(code,msg,commentDao);*//*

        PrintWriter out = res.getWriter();
        out.print(gson.toJson(ro));
        out.close();
    }


*/
