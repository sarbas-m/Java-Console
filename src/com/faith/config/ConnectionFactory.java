package com.faith.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class ConnectionFactory {
	
	//create global static connection null object
	public static Connection connection=null;
	public static Connection getDataBaseConnection() throws Exception{
		try{
			//checking if a connection exists 
			if(connection!=null){
				return connection;
			}
			//access and loading properties file for new Connection
			Properties newProb=loadProperties();
			String driveClass=newProb.getProperty("MYSQLJDBC.MYSQL_DRIVER");
			String url=newProb.getProperty("MYSQLJDBC.MYSQL_URL");
			String username=newProb.getProperty("MYSQLJDBC.USER");
			String password=newProb.getProperty("MYSQLJDBC.PASS");
			String usessl=newProb.getProperty("MYSQLJDBC.USESSL");
			
			//establish connection
			try{
				//step 1.register JDBC driver
				Class.forName(driveClass);
				//get connection 
				connection=DriverManager.getConnection(url+"?usessl="+usessl,username,password);
				return connection;	
			}catch(ClassNotFoundException e){
				throw new Exception("no database......");
			}
			
		}catch(SQLException err){
			throw new RuntimeException("error in connection...");
		}
		
		
	}
	private static Properties loadProperties() throws IOException {
		Properties prop=new Properties();
		prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
		
		return prop;
	}
	public static void main(String[] args) throws Exception{
		connection=ConnectionFactory.getDataBaseConnection();
		System.out.println("Connected to database....");
				
	}

}