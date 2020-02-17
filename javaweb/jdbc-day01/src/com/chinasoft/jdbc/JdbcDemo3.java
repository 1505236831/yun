package com.chinasoft.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 使用jdbc操作DDL语言
 *   create table student(id int,name varchar(20));
 */
public class JdbcDemo3 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;
        try {
            conn = JDBCUtils.getConnection();

            String sql = "create table student(id int,name varchar(20))";

            statement = conn.createStatement();

            int i = statement.executeUpdate(sql);

            System.out.println(i);
            if(i==0){
                System.out.println("表创建成功");
            }else{
                System.out.println("表创建失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(null,statement,conn);
        }

    }
}
