package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private final static String  URL =  "jdbc:postgresql://rev-canada-training.cgz20kzsu2zt.us-east-2.rds.amazonaws.com:5432/ersdb";
	private final static String username = "ersuser";
	private final static String password = "password";
	
	public static Connection getDbConnection() throws SQLException{
		return DriverManager.getConnection(URL, username, password);
}


//public class DBConnection {
//
//	public static Connection createConnection() {
//		Connection con = null;
//		String url = "jdbc:postgresql://rev-canada-training.cgz20kzsu2zt.us-east-2.rds.amazonaws.com:5432/ersdb";
//		String username = "ersuser";
//		String password = "password";
//
//		try {
//			/*
//			try {
//				Class.forName("com.mysql.jdbc.Driver");
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//			*/
//			con = DriverManager.getConnection(url, username, password);
//			System.out.println("Post establishing a DB connection - " + con);
//		} catch (SQLException e) {
//			System.out.println("An error occurred. Maybe user/password is invalid");
//			e.printStackTrace();
//		}
//		return con;
//	}
	
//	public static void main(String[] args) {
//		Connection con = DBConnection();
//		System.out.println(con == null ? "No connection" : "There is connection");
//	}
}