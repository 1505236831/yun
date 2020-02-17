package com.chinasoft.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.Properties;

/**
 * druid 入门使用
 */
public class DruidDemo1 {
    public static void main(String[] args) throws Exception {

//        1.导包 druid-1.0.9.jar
//        2.定义配置文件：格式主要为properties，位置：可以任意位置
//        3.加载配置文件
        Properties pro = new Properties();
        InputStream in = DruidDemo1.class.getClassLoader().getResourceAsStream("druid.properties");
        pro.load(in);
//        4.获取连接池对象 DruidDataSourceFactory
        DataSource  ds = new DruidDataSourceFactory().createDataSource(pro);
//        5.获取连接
        Connection connection = ds.getConnection();
        System.out.println(connection);

    }
}
