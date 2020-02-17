package com.chinasoft.springTemplate;

import com.chinasoft.utils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * springJdbcTemplate入门案例
 */
public class SpringTemplateDemo1 {
    public static void main(String[] args) {

//        1.导包
//        2.创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
//        3.写sql
        String sql = "update account set balance = 1500 where id = 4";
//        4.执行sql
        int i = template.update(sql);
//        5.出结果
        System.out.println(i);

    }
}
