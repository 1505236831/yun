package com.chinasoft.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo2 {

    public static void main(String[] args){

        //2.注册驱动
        //Class.forName("com.mysql.jdbc.Driver");


        Connection conn = null;
        Statement statement = null;
        try {
            //3.获取数据库连接对象
            conn = JDBCUtils.getConnection();
            //4.定义sql
            String sql = "insert into account values(4,'赵六',2550)";
            //5.获取执行SQL对象Statement
            statement = conn.createStatement();
            //6.执行SQL语句，接受返回结果
            int i = statement.executeUpdate(sql);
            //7.处理结果
            if(i >0){
                System.out.println("插入操作执行成功");
            }else{
                System.out.println("插入操作执行失败");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(null,statement,conn);
        }


    }
}
