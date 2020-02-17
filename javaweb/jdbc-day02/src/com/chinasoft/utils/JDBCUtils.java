package com.chinasoft.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 改写jdbc工具类，使用我们新添的连接池技术
 */
public class JDBCUtils {
    private static DataSource dataSource = null;
//    使用静态快先读取文件，建立连接池
    static{
    try {
        Properties pro = new Properties();

//        URL resource = JDBCUtils.class.getResource("druid.properties");
//        pro.load(new FileReader(resource.getPath()));
        pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
        dataSource = DruidDataSourceFactory.createDataSource(pro);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

//    关闭连接，将连接归还到连接池中
    public static void closs(ResultSet rs, Statement st, Connection conn){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(st != null){
            try {
                st.close();
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
//    返回一个连接
    public static  Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

//    返回一个DataSource
    public static DataSource getDataSource(){
        return dataSource;
    }

}
