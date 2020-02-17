package com.chinasoft.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3p0Demo1 {
    public static void main(String[] args) throws SQLException {
//        1.导包：c3p0-0.9.5.2.jar,mchange-commons-java-0.2.12.jar,mysql数据库驱动包
//        2.修改配置文件：修改名称 c3p0.properties或者c3p0-config.xml,放置配置文件的位置：放到src路径下
//        3.创建核心对象 数据库连接池对象 ComboPooleDataSource
        DataSource ds = new ComboPooledDataSource();
//        4.获取连接：getConnection
        Connection connection = ds.getConnection();
        System.out.println(connection);
    }
}
