package com.sectraining.vulnserver.xss;

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

public class HollerDao {
	
	private String getHollers = "SELECT createTime, userName, message FROM HOLLER WHERE createTime >= NOW() - INTERVAL 5 MINUTE ORDER BY createTime;"; 
	public List<Holler> getLatestHollers(){
		Context ctx = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<Holler> hollers = new LinkedList<Holler>();
		try{
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/vulndb");
			
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(getHollers);
			
			while(rs.next()) {
				Holler h = new Holler();
				h.setCreateTime(rs.getDate("createTime"));
				h.setUser(rs.getString("userName"));
				h.setMessage(rs.getString("message"));
				hollers.add(h);
			}
			
		} catch( SQLException e) {
			e.printStackTrace();
		} catch(NamingException ne) {
			ne.printStackTrace();
		}finally {
			try {
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
		return hollers;
	}
	
	private String insertHoller = "INSERT into HOLLER set userName=?, message=?, createTime=NOW();";
	public void insertHoller(Holler h) {
		Context ctx = null;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/vulndb");
			
			con = ds.getConnection();
			ps = con.prepareStatement(insertHoller);
			ps.setString(1, h.getUser());
			ps.setString(2, h.getMessage());
			ps.executeQuery();
		
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (NamingException ne) {
			ne.printStackTrace();
		}finally {
			try {
				ps.close();
				con.close();				
				ctx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException ne) {
				ne.printStackTrace();
			}
		}
	}
}
