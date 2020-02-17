package com.chinasoft.jdbc;

import java.sql.*;

public class JdbcDemo4 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from dept";
            statement = conn.createStatement();
            //执行DQL语句
            resultSet = statement.executeQuery(sql);
            //处理结果
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String dname = resultSet.getString("dname");
                String loc = resultSet.getString("loc");
                System.out.println(id+"  "+dname+"  "+loc);
            }

//            resultSet.next();
//            int anInt1 = resultSet.getInt(1);
//            String string1 = resultSet.getString(2);
//            String string11 = resultSet.getString(3);
//            System.out.println(anInt1+"  "+string1+"  "+string11);
//
//            resultSet.next();
//            int anInt2 = resultSet.getInt(1);
//            String string2 = resultSet.getString(2);
//            String string22 = resultSet.getString(3);
//            System.out.println(anInt2+"  "+string2+"  "+string22);
//
//            resultSet.next();
//            int anInt3 = resultSet.getInt(1);
//            String string3 = resultSet.getString(2);
//            String string33 = resultSet.getString(3);
//            System.out.println(anInt3+"  "+string3+"  "+string33);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,statement,conn);
        }
    }
}
