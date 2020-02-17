package com.chinasoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 步骤：
 *     1.导入驱动jar包 mysql-connecter-java-5.1.37.jar
 * 	        1.直接jar包复制到项目中
 * 	        2.添加到Libaray
 *     2.注册驱动
 *     3.获取数据库连接对象
 *     4.定义sql
 *     5.获取执行SQL对象Statement
 *     6.执行SQL语句，接受返回结果
 *     7.处理结果
 *     8.释放资源
 */
public class JdbcDemo1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {


//        2.注册驱动:Driver底层也是一个DriverManager对象
//        Class.forName("com.mysql.jdbc.Driver");
//        3.获取数据库连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc1","root","root");
//        4.定义sql
        String sql = "update account set balance = 250 where id = 1";
//        5.获取执行SQL对象Statement
        Statement statement = conn.createStatement();
//        6.执行SQL语句，接受返回结果
        int i = statement.executeUpdate(sql);
//        7.处理结果
        System.out.println(i);
//        8.释放资源
        statement.close();
        conn.close();
    }
}
