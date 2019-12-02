package com.scs.web.blog.dao.impl;

import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.domain.vo.ArticleVo;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.util.BeanHandler;
import com.scs.web.blog.util.DbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author
 * @ClassName ArticleDaoImpl
 * @Description 文章Dao接口实现类
 * @Date 2019/11/10
 * @Version 1.0
 **/
public class ArticleDaoImpl implements ArticleDao {
    private static Logger logger = LoggerFactory.getLogger(ArticleDaoImpl.class);

    @Override
    public void batchInsert(List<Article> articleList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO t_article (user_id,topic_id,title,summary,thumbnail,content,likes,comments,create_time) VALUES (?,?,?,?,?,?,?,?,?) ";
        PreparedStatement pst = connection.prepareStatement(sql);
        articleList.forEach(article -> {
            try {
                pst.setLong(1, article.getUserId());
                pst.setLong(2, article.getTopicId());
                pst.setString(3, article.getTitle());
                pst.setString(4, article.getSummary());
                pst.setString(5, article.getThumbnail());
                pst.setString(6, article.getContent());
                pst.setInt(7, article.getLikes());
                pst.setInt(8, article.getComments());
                pst.setObject(9, article.getCreateTime());
                pst.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        pst.executeBatch();
        connection.commit();
        DbUtil.close(connection, pst);
    }

    @Override
    public List<ArticleVo> selectHotArticles() throws SQLException {
        Connection connection = DbUtil.getConnection();
        //从文章、专题、用户表联查出前端需要展示的数据
        String sql = "SELECT a.id,a.user_id,a.topic_id,a.title,a.summary,a.thumbnail,a.comments,a.likes,a.create_time," +
                "b.topic_name,b.logo,c.nickname,c.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_topic b " +
                "ON a.topic_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "ORDER BY a.comments DESC " +
                "LIMIT 10 ";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        //调用封装的方法，将结果集解析成List
        List<ArticleVo> articleVoList = BeanHandler.convertArticle(rs);
        DbUtil.close(connection, pst, rs);
        return articleVoList;
    }

    @Override
    public List<ArticleVo> selectByPage(int currentPage, int count) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT a.*,b.topic_name,b.logo,c.nickname,c.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_topic b " +
                "ON a.topic_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id  LIMIT ?,? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, (currentPage - 1) * count);
        pst.setInt(2, count);
        ResultSet rs = pst.executeQuery();
        List<ArticleVo> articleVos = BeanHandler.convertArticle(rs);
        DbUtil.close(connection, pst, rs);
        return articleVos;
    }


    @Override
    public List<ArticleVo> selectByKeywords(String keywords) throws SQLException {
        Connection connection = DbUtil.getConnection();
        //从文章、专题、用户表联查出前端需要展示的数据
        String sql = "SELECT a.*,b.topic_name,b.logo,c.nickname,c.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_topic b " +
                "ON a.topic_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "WHERE a.title LIKE ?  OR a.summary LIKE ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, "%" + keywords + "%");
        pst.setString(2, "%" + keywords + "%");
        ResultSet rs = pst.executeQuery();
        List<ArticleVo> articleVos = BeanHandler.convertArticle(rs);
        DbUtil.close(connection, pst, rs);
        return articleVos;
    }

    @Override
    public List<ArticleVo> selectByTopicId(long topicId) throws SQLException {
        Connection connection = DbUtil.getConnection();
        //从文章、专题、用户表联查出前端需要展示的数据
        String sql = "SELECT a.*,b.topic_name,b.logo,c.nickname,c.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_topic b " +
                "ON a.topic_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "WHERE a.topic_id = ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, topicId);
        ResultSet rs = pst.executeQuery();
        List<ArticleVo> articleVos = BeanHandler.convertArticle(rs);
        DbUtil.close(connection, pst, rs);
        return articleVos;
    }

    @Override
    public List<ArticleVo> selectByUserId(long userId) throws SQLException {
        Connection connection = DbUtil.getConnection();
        //从文章、专题、用户表联查出前端需要展示的数据
        String sql = "SELECT a.*,b.topic_name,b.logo,c.nickname,c.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_topic b " +
                "ON a.topic_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "WHERE a.topic_id = ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, userId);
        ResultSet rs = pst.executeQuery();
        List<ArticleVo> articleVos = BeanHandler.convertArticle(rs);
        DbUtil.close(connection, pst, rs);
        return articleVos;
    }

    @Override
    public ArticleVo getArticle(long id) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT a.*,b.topic_name,b.logo,c.nickname,c.avatar " +
                "FROM t_article a " +
                "LEFT JOIN t_topic b " +
                "ON a.topic_id = b.id " +
                "LEFT JOIN t_user c " +
                "ON a.user_id = c.id " +
                "WHERE a.id = ?  ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        ArticleVo articleVo = BeanHandler.convertArticle(rs).get(0);
        //注意这里，上一步执行完毕后，结果集的指针已经在当前这行记录的下方，所以回退一下
        rs.previous();
        //列表页的文章数据一般不需要详细内容，但是文章详情页需要，所以补上content属性
        articleVo.getArticle().setContent(rs.getString("content"));
        DbUtil.close(connection, pst, rs);
        return articleVo;
    }




}
