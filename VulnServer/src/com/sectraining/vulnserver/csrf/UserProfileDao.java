package com.sectraining.vulnserver.csrf;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		
		// TODO: Make this safe. This is CSRF, not SQLi.
		String query = "SELECT * FROM SQLI_USERS WHERE USERNAME=? AND PASSWORD=?;";
		
		Context ctx = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		try{
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/vulndb");
			
			con = ds.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			rs = ps.executeQuery();
			
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
				ps.close();
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
		
		String query = "SELECT * FROM SQLI_USERS WHERE UID=?;";
		
		Context ctx = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try{
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/vulndb");
			
			con = ds.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, uid);
			rs = ps.executeQuery();
			
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
				con.close();
				ps.close();
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
		
		String query = "UPDATE SQLI_USERS SET USERNAME=?, PASSWORD=?, EMAIL=?, SSN=?, CCN=? WHERE UID =?;";
		
		Context ctx = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try{
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/vulndb");
			
			con = ds.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getSsn());
			ps.setString(5, user.getCcn());
			ps.setInt(6, user.getUid());
			rs = ps.executeQuery();
						
		} catch( SQLException e) {
			e.printStackTrace();
		} catch(NamingException ne) {
			ne.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
				ps.close();
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
