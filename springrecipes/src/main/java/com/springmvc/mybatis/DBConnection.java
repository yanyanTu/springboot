package com.springmvc.mybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DBConnection {
    private static  Logger logger = LoggerFactory.getLogger(DBConnection.class);
    public static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/mydata";
    public static final String DBUSER = "1234";
    public static final String DBPASS ="";

    static{
        try {
            Class.forName(DBDRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.error("加载驱动异常", e);
        }
    }

    public static Connection getConnection(){
        Connection connection = null ;
        try {
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("创建数据库连接异常", e);
        }
        return connection;
    }

    public static  void close(ResultSet resultSet, Statement statement, Connection connection){
        try {
            if( resultSet != null ){
                resultSet.close();
            }
            if( statement !=null ){
                statement.close();
            }
            if( connection != null ){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("关闭数据库连接异常", e);
        }
    }

    public static void close( Statement statement, Connection connection){
        close(null, statement, connection);
    }
}
