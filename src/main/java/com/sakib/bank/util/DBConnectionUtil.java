package com.sakib.bank.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionUtil {
	
	private static final String URL;
	private static final String USER;
	private static final String PASSWORD;
	private static final String DRIVER;
	
	static {
		Properties props= new Properties();
		try(InputStream input = DBConnectionUtil.class.getClassLoader()
				.getResourceAsStream("db/database.properties"))
		{
			if(input == null ) {
				throw new RuntimeException("database.properties not found on class path");
			}
			
			props.load(input);
			URL= props.getProperty("db.url");
			USER= props.getProperty("db.username");
			PASSWORD= props.getProperty("db.password");
			DRIVER= props.getProperty("db.driver");
			
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Driver is not loaded ",e);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Failed to connect to database.properties",e);
		}
	}
	
	private DBConnectionUtil() {
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(URL,USER, PASSWORD);
		}
		catch(SQLException e) {
			throw new RuntimeException("Failed to connect to database",e);
		}
	}
}
