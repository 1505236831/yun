package com.chinasoft.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * PrepareStatement:
 *      1.防止sql注入
 *      2.执行效率比较高，只预编译一次，用?代替每个字段，不需要在意字段类型
 *      3.提高了程序的可读性
 *
 */
public class JdbcDemo7 {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet resultSet = null;

        try {
            //1.注册驱动
            //省略Class.forName("com.mysql.jdbc.Driver");
            //2.获取连接对象
            conn = JDBCUtils.getConnection();
            //3.定义sql语句
            String sql = "select * from t_user where name = ? and password = ?";
            //4.获取执行sql语句的对象PrepareStatement
            pre = conn.prepareStatement(sql);
            //5.给sql语句赋值
            //用两个String类型变量接受输入的用户名和密码
            Scanner scan = new Scanner(System.in);
            System.out.println("请输入用户名");
            String name = scan.nextLine();
            System.out.println("请输入密码");
            String password = scan.nextLine();
            //组装pre对象中的sql语句
            pre.setString(1,name);
            pre.setString(2,password);
            //6.执行sql并返回结果
            resultSet = pre.executeQuery();
            //7.处理结果
            if(resultSet.next()){
                System.out.println("登陆成功");
            }else{
                System.out.println("登录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //8.释放资源
        JDBCUtils.close(resultSet,pre,conn);
        }

    }
}
