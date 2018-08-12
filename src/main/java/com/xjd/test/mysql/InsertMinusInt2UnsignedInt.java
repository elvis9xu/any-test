package com.xjd.test.mysql;

import java.sql.*;

/**
 * @author elvis.xu
 * @since 2015-03-22 13:00
 */
public class InsertMinusInt2UnsignedInt {
	public static void main(String[] args) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			String databaseName = "test";
			String host = "119.23.148.103";
			String port = "3306";
			String username = "access";// 用户名(api key);
			String password = "accessdb2017";// 密码(secret key)
			String driverName = "com.mysql.jdbc.Driver";
			String dbUrl = "jdbc:mysql://";
			String serverName = host + ":" + port + "/";
			String connName = dbUrl + serverName + databaseName;
			Class.forName(driverName);
			System.out.println("开始...");
			connection = DriverManager.getConnection(connName, username, password);
//			stmt = connection.createStatement();
			/* 至此连接已完全建立，就可对当前数据库进行相应的操作了 */
			/* 3. 接下来就可以使用其它标准mysql函数操作进行数据库操作 */
			// 创建一个数据库表
//			sql = "create table if not exists test_mysql(" + "id int primary key auto_increment," + "no int, "
//					+ "name varchar(1024)," + "key idx_no(no))";
//			sql = "insert into tests_unsign values(3232235777)";
//			stmt.execute(sql);

			PreparedStatement preparedStatement = connection.prepareStatement("insert into tests_unsign values(?) ");
//			preparedStatement.setLong(1, 3232235777L);
			preparedStatement.setInt(1, (int)3232235777L);
			preparedStatement.execute();
			System.out.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
