package com.scs.web.blog.util;

import com.scs.web.blog.entity.Article;
import com.scs.web.blog.entity.Topic;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.factory.DaoFactory;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mq_xu
 * @ClassName JSoupSpider
 * @Description JSoup爬虫，抓取数据
 * @Date 9:13 2019/11/7
 * @Version 1.0
 **/
public class JSoupSpider {
    private static Logger logger = LoggerFactory.getLogger(JSoupSpider.class);

    private static final int PAGE_COUNT = 1;

    /**
     * 爬取简书网热门作者数据
     *
     * @return
     */
    public static List<User> getUsers() {
        Document document = null;
        List<User> userList = new ArrayList<>(100);
        for (int i = 1; i <= PAGE_COUNT; i++) {
            try {
                document = Jsoup.connect("https://www.jianshu.com/recommendations/users?utm_source=desktop&utm_medium=index-users&page=" + i).get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            assert document != null;
            Elements divs = document.getElementsByClass("col-xs-8");
            divs.forEach(div -> {
                Element wrapDiv = div.child(0);
                Element link = wrapDiv.child(0);
                Elements linkChildren = link.children();
                String introduction = linkChildren.get(2).text();
                if (introduction != null && !"".equals(introduction)) {
                    User user = new User();
                    String linkHref = link.attr("href");
                    user.setMobile(DataUtil.getMobile());
                    user.setPassword(DataUtil.getPassword());
                    user.setGender(DataUtil.getGender());
                    String imgUrl = "https:" + linkChildren.get(0).attr("src");
                    user.setAvatar(imgUrl);
                    user.setNickname(linkChildren.get(1).text());
                    user.setIntroduction(introduction);
                    //头像暂作为背景banner
                    user.setBanner(imgUrl);
                    user.setHomepage("https://www.jianshu.com" + linkHref);
                    user.setBirthday(DataUtil.getBirthday());
                    user.setAddress(DataUtil.getAddress());
                    user.setCreateTime(DataUtil.getCreateTime());
                    user.setStatus((short) 1);
                    userList.add(user);
                }
            });
        }
        return userList;
    }

    /**
     * 爬取简书网文章
     *
     * @return
     */
    public static List<Article> getArticles() {
        List<Topic> topicList = null;
        try {
            topicList = DaoFactory.getTopicDaoInstance().selectAll();
        } catch (SQLException e) {
            logger.error("查询所有专题失败");
        }
        assert topicList != null;
        int len = topicList.size();
        Document document = null;
        List<Article> articleList = new ArrayList<>(100);
        for (Topic topic : topicList) {
            try {
                document = Jsoup.connect(topic.getHomepage()).get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            assert document != null;
            Elements divs = document.getElementsByClass("have-img");
            divs.forEach(div -> {
                String articleUrl = div.child(0).attr("href");
                Document document1 = null;
                try {
                    document1 = Jsoup.connect("https://www.jianshu.com" + articleUrl).get();
                } catch (IOException e) {
                    logger.error("连接失败");
                }
                assert document1 != null;
                Element articleElement = document1.getElementsByClass("_2rhmJa").get(0);
                Article article = new Article();
                article.setContent(articleElement.html());
                Elements elements = div.children();
                Element linkElement = elements.get(0);
                Element divElement = elements.get(1);
                article.setUserId(DataUtil.getUserId());
                article.setTopicId(DataUtil.getUserId());
                article.setTitle(divElement.child(0).text());
                article.setSummary(divElement.child(1).text());
                String img = "https:" + linkElement.child(0).attr("src");
                int index = img.indexOf("?");
                article.setThumbnail(img.substring(0, index));
                Elements metaChildren = divElement.child(2).children();
                String comments = metaChildren.get(2).text();
                String likes = metaChildren.last().text();
                try {
                    article.setComments(Integer.parseInt(comments));
                    article.setLikes(Integer.parseInt(likes));
                } catch (NumberFormatException e) {
                    logger.error("格式转换异常");
                }
                article.setCreateTime(DataUtil.getCreateTime());
                articleList.add(article);
            });
        }
        return articleList;
    }

    /**
     * 爬取简书网专题
     *
     * @return
     */
    public static List<Topic> getTopics() {
        List<Topic> topicList = new ArrayList<>(100);
        Connection connection;
        Document document = null;
        for (int i = 1; i <= PAGE_COUNT; i++) {
            try {
                //分析页面得到url和惨
                connection = Jsoup.connect("https://www.jianshu.com/recommendations/collections?order_by=hot&page=" + i);
                //通过chrome开发者工具查看该请求必须添加请求头
                connection.header("X-PJAX", "true");
                document = connection.get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            assert document != null;
            Elements list = document.select(".collection-wrap");
            list.forEach(item -> {
                Elements elements = item.children();
                Topic topic = new Topic();
                Element link = elements.select("a").get(0);
                Element logo = link.child(0);
                Element name = link.child(1);
                Element description = link.child(2);
                Element articles = elements.select(".count > a").get(0);
                Element follows = elements.select(".count > a").get(0);
                topic.setAdminId(1L);
                topic.setTopicName(name.text());
                topic.setLogo(logo.attr("src"));
                topic.setDescription(description.text());
                topic.setHomepage("https://www.jianshu.com" + link.attr("href"));
                String[] str = StringUtil.getDigital(articles.text());
                topic.setArticles(Integer.parseInt(str[0]));
                str = StringUtil.getDigital(follows.text());
                topic.setFollows(Integer.parseInt(str[0]));
                topic.setCreateTime(DataUtil.getCreateTime());
                topicList.add(topic);
            });
        }
        return topicList;
    }

    public static void main(String[] args) {
        List<User> userList = JSoupSpider.getUsers();
        System.out.println(userList.size());
        userList.forEach(System.out::println);
    }
}
