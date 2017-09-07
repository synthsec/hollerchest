package com.sectraining.vulnserver.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class StartupListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// Do nothing

	}

	// Persistent XSS Initialization 
	private static String xssDropIfExists = "DROP TABLE IF EXISTS HOLLER;";
	private static String xssCreateHoller = "CREATE TABLE HOLLER ("
			+ "createTime DATETIME,"
			+ "userName VARCHAR(30),"
			+ "message VARCHAR(255));";
	
	// SQLi Initialization
	private static String sqliDropIfExists = "DROP TABLE IF EXISTS SQLI_USERS;";
	private static String sqliAddUserTable = "CREATE TABLE SQLI_USERS ("
			+ "USERNAME VARCHAR(255),"
			+ "PASSWORD VARCHAR(255));";
	private static String sqliAddUserOne = "INSERT INTO SQLI_USERS(USERNAME, PASSWORD) "
			+ "VALUES ('admin', 'password1');";
	private static String sqliAddUserTwo = "INSERT INTO SQLI_USERS(USERNAME, PASSWORD) "
			+ "VALUES ('user1', 'mydumbp4ssw0rd');";
	private static String sqliAddUserThr = "INSERT INTO SQLI_USERS(USERNAME, PASSWORD) "
			+ "VALUES ('user2', 'f47herkr4ng');";
	
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Context ctx = null;
		Connection con = null;
		Statement stmt = null;
		try{
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/vulndb");
			
			con = ds.getConnection();
			stmt = con.createStatement();
			
			// Persistent XSS Initialization 
			stmt.executeUpdate(xssDropIfExists);
			stmt.executeUpdate(xssCreateHoller);
			// SQLi Initialization
			stmt.executeUpdate(sqliDropIfExists);
			stmt.executeUpdate(sqliAddUserTable);
			stmt.executeUpdate(sqliAddUserOne);
		
		} catch(Exception ex) {
			System.out.println("Failed to create database");
			ex.printStackTrace();
		}finally {
			try {
				stmt.close();
				con.close();
				ctx.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NamingException ne) {
				ne.printStackTrace();
			}		
		}
	}
}
