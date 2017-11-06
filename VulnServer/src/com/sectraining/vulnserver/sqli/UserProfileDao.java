package com.sectraining.vulnserver.sqli;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserProfileDao {
		
	public UserProfile attemptLogin(UserProfile user){
		UserProfile authUser = new UserProfile();

		String query = String.format("SELECT * FROM SQLI_USERS WHERE USERNAME IN ('%s') AND PASSWORD IN ('%s');", user.getUsername(), user.getPassword());
		
		Context ctx = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/vulndb");
			
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			if (rs.next())
				authUser.setUid(rs.getInt("UID"));
			
		} catch( SQLException e) {
			e.printStackTrace();
		} catch(NamingException ne) {
			ne.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
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
		return authUser;
	}
	
	public UserProfile getUser(int uid){
		UserProfile fetchedUser = new UserProfile();
		
		// TODO: Make this safe. This is CSRF, not SQLi.
		String query = String.format("SELECT * FROM SQLI_USERS WHERE UID='%d';", uid);
		
		Context ctx = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/vulndb");
			
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			if (rs.next())
			{
				fetchedUser.setUsername(rs.getString("USERNAME"));
				fetchedUser.setPassword(rs.getString("PASSWORD"));
				fetchedUser.setEmail(rs.getString("EMAIL"));
				fetchedUser.setSsn(rs.getString("SSN"));
				fetchedUser.setCcn(rs.getString("CCN"));
			}
			
		} catch( SQLException e) {
			e.printStackTrace();
		} catch(NamingException ne) {
			ne.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
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
		return fetchedUser;
	}
	
	public void updateUser(UserProfile user){
		
		// TODO: Make this safe. This is CSRF, not SQLi.
		String query = String.format("UPDATE SQLI_USERS SET USERNAME = '%s', PASSWORD = '%s', EMAIL = '%s', SSN = '%s', CCN = '%s' WHERE UID = %d;", user.getUsername(), user.getPassword(), user.getEmail(), user.getSsn(), user.getCcn(), user.getUid());
		
		Context ctx = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/vulndb");
			
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
						
		} catch( SQLException e) {
			e.printStackTrace();
		} catch(NamingException ne) {
			ne.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
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
		return;
	}
	
	
}
