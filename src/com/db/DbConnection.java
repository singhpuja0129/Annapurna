package com.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	
	private static Connection con;
	
	public static Connection getDbConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/annapurna", "root","puja");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return con;
	}
	
	
}
