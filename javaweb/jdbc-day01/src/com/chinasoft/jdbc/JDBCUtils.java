package com.chinasoft.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * jdbc工具类
 */
public class JDBCUtils {
    private static String url;
    private static String username;
    private static String password;
    private static String driver;

    /**
     * 用静态块来加载资源，读取图片，音频，视频，配置文件
     */
    static{
        try {
            //1.创建Properties对象
            Properties pro = new Properties();
              //获取jdbc.properties文件路径--》 ClassLoader
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();
            URL res = classLoader.getResource("jdbc.properties");
            String path = res.getPath();
//            System.out.println(path);

            //2.加载文件
//            pro.load(new FileReader("src\\jdbc.properties"));
            pro.load(new FileReader(path));
            //3.获取数据并赋值
            url = pro.getProperty("url");
            username = pro.getProperty("username");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            //4.将值传给对应的方法
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接对象
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * 资源关闭的方法
     * @param resultSet
     * @param statement
     * @param conn
     */
    public static void close(ResultSet resultSet, Statement statement, Connection conn){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
