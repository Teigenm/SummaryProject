package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbDao {
	private static String Driver="com.mysql.jdbc.Driver";
	private static String URL="jdbc:mysql://localhost:3306/test";
	private static String USERNAME="root";
	private static String PASSWORD="123456";
	protected Connection con;
	protected PreparedStatement ps;
	protected ResultSet rs;
	
	static {
		try {
			Class.forName(Driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void getConnection() throws SQLException{
		con= DriverManager.getConnection(URL,USERNAME,PASSWORD);
	}
	public void close() {
		try {
			if(con!=null)
				con.close();
			if(ps!=null)
				ps.close();
			if(rs!=null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ResultSet query(String sql) {
		try {
			getConnection();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public int update(String sql) {
		int cnt=-1;
		try {
			getConnection();
			ps=con.prepareStatement(sql);
			cnt=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return cnt;
	}
}
