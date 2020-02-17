package com.chinasoft.druid;

import com.chinasoft.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

/**
 * 使用工具类向emp表中插入一条数据
 */
public class DruidDemo2 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = JDBCUtils.getConnection();
//            String sql = "insert into emp values(1015,'律政先锋',3,1006,'2001-01-29',7000,null,1)";
            String sql = "insert into emp values(?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);

            ps.setInt(1,1015);
            ps.setString(2,"曹操");
            ps.setInt(3,3);
            ps.setInt(4,1007);
            ps.setDate(5,new Date(2001-01-29));
            ps.setDouble(6,8000);
            ps.setDouble(7,200);
            ps.setInt(8,2);

            int i = ps.executeUpdate();
            if(i > 0){
                System.out.println("插入成功");
            }else{
                System.out.println("插入失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closs(null,ps,conn);
        }

    }
}
