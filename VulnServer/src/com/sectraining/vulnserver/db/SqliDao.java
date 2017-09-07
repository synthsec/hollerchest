package com.sectraining.vulnserver.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SqliDao {
		
	public UserProfile attemptLogin(UserProfile user){
		UserProfile authUser = new UserProfile();
		
		String query = String.format("SELECT * FROM SQLI_USERS WHERE USERNAME='%s' AND PASSWORD='%s';", user.getUsername(), user.getPassword());
		
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
				authUser.setUsername(rs.getString("USERNAME"));
			
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
	
}
