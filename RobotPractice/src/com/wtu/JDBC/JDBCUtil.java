package com.wtu.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtil {
	// ��صĳ�Ա����
	private Connection con;
	private PreparedStatement ps;

	// ���췽������������ļ��ؼ����ӵĽ���
	public JDBCUtil() {
		// 1.��������:����Ϊ��Ҫ���ص����·��������
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 2.������DBMS�����������ַ������û���������
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

	// ����SQL�������һ��PreparedStatment����
	public PreparedStatement createPst(String sql) {
		try {
			ps = con.prepareStatement(sql);
			return ps;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// �ͷ���ص���Դ
	public void close() {
		try {
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
