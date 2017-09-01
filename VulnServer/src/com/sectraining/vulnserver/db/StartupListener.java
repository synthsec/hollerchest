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

	private static String dropIfExists = "DROP TABLE IF EXISTS HOLLER;";
	private static String createHoller = "CREATE TABLE HOLLER ("
			+ "createTime DATETIME,"
			+ "userName VARCHAR(30),"
			+ "message VARCHAR(255));";
	
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
			stmt.executeUpdate(dropIfExists);
			stmt.executeUpdate(createHoller);
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
