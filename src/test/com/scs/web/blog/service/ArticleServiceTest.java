package com.scs.web.blog.service;

import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.util.Result;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArticleServiceTest {
    private ArticleService articleService = ServiceFactory.getArticleServiceInstance();

    @Test
    public void getHotArticles() {
       Result result =  articleService.getHotArticles();
        System.out.println(result.getData());
    }

    @Test
    public void getPageArticles() {
    }

    @Test
    public void getArticle() {
    }
}