package com.xjd.test.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DruidStatDemo {
    public static void main(String[] args) throws SQLException, IOException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://119.23.148.103:3306/test");
        dataSource.setUsername("access");
        dataSource.setPassword("accessdb2017");
        dataSource.setFilters("mergeStat,slf4j"); // 监控配置

        DruidPooledConnection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        for (int i = 0; i <= 10; i++) {
            ResultSet resultSet = statement.executeQuery("select * from test");
            resultSet.close();
        }
        connection.close();

        System.in.read();
    }
}
