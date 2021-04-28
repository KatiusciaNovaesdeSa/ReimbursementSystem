package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ReimConnection {

	//Logger log = Logger.getRootLogger();
	
	public static String URL;
	
	public static String USERNAME;
	
	public static String PASSWORD;
	
	private static ReimConnection connectionFactory = null;
	
	private ReimConnection() {
		
		
		 URL =  "jdbc:postgresql://rev-canada-training.cgz20kzsu2zt.us-east-2.rds.amazonaws.com:5432/ersdb";
		 USERNAME = "ersuser";
		 PASSWORD = "password";
		
		
	}
	
	public Connection createConnection() {
		
		try {
			Class.forName("org.postgresql.Driver");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		try {
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		catch (SQLException e) {
		//	log.error("Failed to connect to DB", e);
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static synchronized Connection getConnection() {
		
		if (connectionFactory == null) {
			connectionFactory = new ReimConnection();
		}
		
		return connectionFactory.createConnection();
		
	}

}
