package com.chinasoft.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 使用jdbc实现登录功能
 *
 *  1.在数据库中查询是否有你前期注册好的账号和密码
 *  2.如果查到账号密码然后跟你输入的账号密码相匹配则显示登陆成功
 *  3.账号存在，但是密码不正确则显示密码错误登录不成功，请重新输入密码
 */
public class JdbcDemo6 {
    public static void main(String[] args) {

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<TUser> list = new ArrayList<>();

        //用两个String类型变量接受输入的用户名和密码
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入用户名");
        String name1 = scan.next();
        System.out.println("请输入密码");
        String password1 = scan.next();

        try {
            conn = JDBCUtils.getConnection();
            statement = conn.createStatement();
            String sql = "select * from t_user";
            resultSet = statement.executeQuery(sql);

            //循环读取resultSet里的每一行数据
            while(resultSet.next()){
                TUser user = new TUser();
                //获取一行字段
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String pwd = resultSet.getString(3);

                //将读取到一行的各个字段装入一个TUser对象中
                user.setId(id);
                user.setName(name);
                user.setPassword(pwd);

                //将对象装入list集合中
                list.add(user);
            }

            //布尔值确定是否有匹配的用户名或密码，匹配上则改为true
            boolean flag1 = false;//匹配用户名
            boolean flag2 = false;//匹配密码
            //遍历所有用户
            for (TUser user : list) {
                //如果用户名匹配则将flag1值改为true
                if(user.getName().equals(name1)) {
                    flag1 = true;
                    //在用户名匹配上的情况下，如果密码匹配则将flag2值改为true
                    if (user.getPassword().equals(password1)) flag2 = true;
                }
            }

            //判断用户输入的用户名和密码，并给出对应的输出
            if(flag1){
                if(flag2) System.out.println("恭喜您，登陆成功");
                else System.out.println("密码错误，登录失败");
            }else{
                System.out.println("用户名或密码错误，登录失败");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,statement,conn);
        }

    }
}
