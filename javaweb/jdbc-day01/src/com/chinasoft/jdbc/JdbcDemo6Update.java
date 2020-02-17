package com.chinasoft.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcDemo6Update {
    public static void main(String[] args) {

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
//        List<TUser> list = new ArrayList<>();

        //用两个String类型变量接受输入的用户名和密码
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入用户名");
        String name1 = scan.nextLine();
        System.out.println("请输入密码");
        String password1 = scan.nextLine();

        try {
            conn = JDBCUtils.getConnection();
            statement = conn.createStatement();
            String sql1 = "select * from t_user where name ='"+name1+"'";
            String sql2 = "select * from t_user where password ='"+  password1 +"'";
            resultSet = statement.executeQuery(sql1);

            boolean flag = false;

            //循环读取resultSet里的每一行数据
            while(resultSet.next()){
                resultSet = statement.executeQuery(sql2);
                if(resultSet.next()){
                    flag = true;
                    System.out.println("恭喜您，登陆成功");
                    break;
                }else{
                    flag = true;
                    System.out.println("密码错误，登录失败");
                    break;
                }
            }
            if(!flag) System.out.println("对不起，请检查您的用户名");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,statement,conn);
        }
    }
}
