package com.scs.web.blog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author mq_xu
 * @ClassName DbUtil
 * @Description 数据库连接工具类
 * @Date 2019/11/10
 * @Version 1.0
 **/
public class DbUtil {
    private static Logger logger = LoggerFactory.getLogger(DbUtil.class);

    private static Properties properties;

    private DbUtil() {
    }

    // 使用静态代码块保证在类加载的时候立即加载对应的配置文件
    static {
        properties = new Properties();
        try {
            InputStream ins = DbUtil.class.getClassLoader().getResourceAsStream("db-config.properties");
            assert ins != null;
            properties.load(ins);
            Class.forName(properties.getProperty("jdbc.driverClassName"));
        } catch (FileNotFoundException e) {
            logger.error("数据库配置文件未找到");
        } catch (IOException e) {
            logger.error("数据库配置文件读写错误");
        } catch (ClassNotFoundException e) {
            logger.error("数据库驱动 未找到");
        }
    }


    /**
     * 获得数据库连接Connection
     *
     * @return Connection 数据库连接
     */
    public static Connection getConnection() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    properties.getProperty("jdbc.url"),
                    properties.getProperty("jdbc.username"),
                    properties.getProperty("jdbc.password"));
        } catch (SQLException e) {
            logger.error("数据库连接失败");
        }
        return connection;
    }

    /**
     * 关闭connection
     *
     * @param connection 连接池对象
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭Statement
     *
     * @param statement
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭ResultSet
     *
     * @param resultSet
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭Connection 以及Statement
     *
     * @param connection
     * @param statement
     */
    public static void close(Connection connection, Statement statement) {
        close(connection);
        close(statement);
    }

    /**
     * 关闭Connection，Statement以及ResultSet
     *
     * @param connection
     * @param statement
     * @param resultSet
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        close(connection, statement);
        close(resultSet);
    }
}