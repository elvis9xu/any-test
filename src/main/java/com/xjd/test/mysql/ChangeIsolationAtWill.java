package com.xjd.test.mysql;

import java.sql.*;

/**
 * @author elvis.xu
 * @since 2015-03-22 13:00
 */
public class ChangeIsolationAtWill {
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
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			connection.setAutoCommit(false);

			{
				PreparedStatement preparedStatement = connection.prepareStatement("insert into tests_unsign values(?) ");
				preparedStatement.setLong(1, 1L);
				preparedStatement.execute();
			}

			connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
			{
				PreparedStatement preparedStatement = connection.prepareStatement("select * from tests_unsign");
				ResultSet resultSet = preparedStatement.executeQuery();
				System.out.println("================");
				while (resultSet.next()) {
					System.out.println(resultSet.getLong(1));
				}
			}

			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			{
				PreparedStatement preparedStatement = connection.prepareStatement("select * from tests_unsign");
				ResultSet resultSet = preparedStatement.executeQuery();
				System.out.println("================");
				while (resultSet.next()) {
					System.out.println(resultSet.getLong(1));
				}
			}

			System.out.println("OK");
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
