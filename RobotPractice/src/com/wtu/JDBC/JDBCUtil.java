package com.wtu.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtil {
	// 相关的成员变量
	private Connection con;
	private PreparedStatement ps;

	// 构造方法，完成驱动的加载及连接的建立
	public JDBCUtil() {
		// 1.加载驱动:参数为需要加载的类的路径及名字
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 2.建立与DBMS的连接连接字符串、用户名、密码
			String url, username, userpwd;
			url = "jdbc:mysql://localhost:3306/robot?useSSL=true&haracterEncoding=utf-8&useOldAliasMetadataBehavior=true";
			username = "root";
			userpwd = "Momoling123123";
			con = DriverManager.getConnection(url, username, userpwd);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 利用SQL语句生成一个PreparedStatment对象
	public PreparedStatement createPst(String sql) {
		try {
			ps = con.prepareStatement(sql);
			return ps;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 释放相关的资源
	public void close() {
		try {
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
