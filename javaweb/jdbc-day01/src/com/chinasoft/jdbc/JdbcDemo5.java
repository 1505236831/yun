//package com.chinasoft;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class JdbcDemo5 {
//    public static void main(String[] args) {
//
//        List<Emp> all = findAll();
//        System.out.println(all);
////        for (Emp e : all) {
////                System.out.println(e);
////            }
//    }
//
//    private static List<Emp> findAll() {
//        Connection conn = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//        List<Emp> list = new ArrayList<>();
//
//        try {
//            conn = JDBCUtils.getConnection();
//            statement = conn.createStatement();
//            String sql = "select * from emp";
//            resultSet = statement.executeQuery(sql);
//            while(resultSet.next()){
//                int id = resultSet.getInt("id");
//                String ename = resultSet.getString("ename");
//                int jobId = resultSet.getInt("job_id");
//                int mgr = resultSet.getInt("mgr");
//                Date joindate = resultSet.getDate("joindate");
//                double salary = resultSet.getDouble("salary");
//                double bonus = resultSet.getDouble("bonus");
//                int deptId = resultSet.getInt("dept_id");
//
//                Emp emp = new Emp();
//                emp.setId(id);
//                emp.setEname(ename);
//                emp.setJobId(jobId);
//                emp.setMgr(mgr);
//                emp.setJoindate(joindate);
//                emp.setSalary(salary);
//                emp.setBonus(bonus);
//                emp.setDeptId(deptId);
//
//                list.add(emp);
//
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JDBCUtils.close(resultSet,statement,conn);
//        }
//        return list;
//    }
//}





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
 * 1.在数据库中查询是否有你前期注册好的账号和密码
 * 2.如果查到账号密码 然后跟你输入的账号密码相匹配则会显示登录成功
 * 3.账号存在 但是密码不正确则显示密码错误登录不成功，请重新输入密码
 * */
public class JdbcDemo5 {
    static Connection conn = null;
    static Statement sta = null;
    static ResultSet resultSet = null;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("please input name");
        String n = scan.next();
        System.out.println("please input password");
        String p = scan.next();

        Connection conn = null;
        Statement sta = null;
        ResultSet resultSet = null;
        List<TUser> list = new ArrayList<>();
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from t_user where name = '"+n+"' and password = '"+p+"';";
            sta = conn.createStatement();
            //执行DQL
            resultSet = sta.executeQuery(sql);

            //遍历结果集
            if (resultSet.next()){
                System.out.println("登录成功");
            }else {
                LoginOut(n,p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,sta,conn);
        }
    }


    private static void LoginOut(String n,String p) {
        try {
            String sql1 = "select * from t_user where name = '"+n+"' or password = '"+p+"'";
            resultSet = sta.executeQuery(sql1);
            if (resultSet.next()){
                System.out.println("用户名或者密码错误");
            }else {
                System.out.println("登录失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}