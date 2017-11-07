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
	
	// SQLi + CSRF Initialization
	private static String sqliDropIfExists = "DROP TABLE IF EXISTS SQLI_USERS;";
	private static String sqliAddUserTable = "CREATE TABLE SQLI_USERS ("
			+ "UID INT,"
			+ "USERNAME VARCHAR(255),"
			+ "PASSWORD VARCHAR(255),"
			+ "EMAIL VARCHAR(255),"
			+ "SSN VARCHAR(255),"
			+ "CCN VARCHAR(255));";

	private static String sqliAddUserTwo = "INSERT INTO SQLI_USERS(UID, USERNAME, PASSWORD, EMAIL, SSN, CCN) "
			+ "VALUES (666, 'user1', 'password2', 'user1@internet.com', '310-23-6765', '4539944435754586');";
	private static String sqliAddUserThr = "INSERT INTO SQLI_USERS(UID, USERNAME, PASSWORD, EMAIL, SSN, CCN) "
			+ "VALUES (420, 'user2', 'password3', 'user2@internet.com', '520-44-0278', '4916045792579088');";
	private static String sqliAddUserOne = "INSERT INTO SQLI_USERS(UID, USERNAME, PASSWORD, EMAIL, SSN, CCN) "
			+ "VALUES (123, 'administrator', 'password1', 'admin@internet.com', '352-10-6257', '4532654409131045');";
	private static String sqliAddUserFou = "INSERT INTO SQLI_USERS(UID, USERNAME, PASSWORD, EMAIL, SSN, CCN) "
			+ "VALUES (187, 'user3', 'password4', 'user3@internet.com', '504-60-7151', '4024007160709415');";
	private static String sqliAddUserFiv = "INSERT INTO SQLI_USERS(UID, USERNAME, PASSWORD, EMAIL, SSN, CCN) "
			+ "VALUES (333, 'user4', 'password5', 'user4@internet.com', '269-46-2503', '4532575195856331');";

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
			// SQLi + CSRF Initialization
			stmt.executeUpdate(sqliDropIfExists);
			stmt.executeUpdate(sqliAddUserTable);
			stmt.executeUpdate(sqliAddUserOne);
			stmt.executeUpdate(sqliAddUserTwo);
			stmt.executeUpdate(sqliAddUserThr);
			stmt.executeUpdate(sqliAddUserFou);
			stmt.executeUpdate(sqliAddUserFiv);
		
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
