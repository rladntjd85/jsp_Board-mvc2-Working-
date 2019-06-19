package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class MDao {

	DataSource dataSource;
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	public MDao() {
		// TODO Auto-generated constructor stub
		
		try {
			InitialContext initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/bbs");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public int ConfirmId(String userID) {
		// TODO Auto-generated method stub
		
		String sql = "select userID from users where userID = ?";
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, userID);
			rs = ps.executeQuery();
			
			if(rs.next() || userID.equals("")) {
				return 0;//이미존재하는회원
			}else {
				return 1; //가입가능함
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("회원조회 오류");
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null) try{rs.close();}catch(SQLException ex){}
				if(ps!=null) try{ps.close();}catch(SQLException ex){}
				if(ps!=null) try{ps.close();}catch(SQLException ex){}
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return -1;//오류
		
	}
	
	 public int join(String userID, String userPassword, String userName, 
             String userAge, String userGender, String userEmail){

			String SQL = "INSERT INTO USERS VALUE(?,?,?,?,?,?)";
			
			try {
			 conn = dataSource.getConnection();
			 ps = conn.prepareStatement(SQL);
			 ps.setString(1, userID);
			 ps.setString(2, userPassword);
			 ps.setString(3, userName);
			 ps.setInt(4, Integer.parseInt(userAge));
			 ps.setString(5, userGender);
			 ps.setString(6, userEmail);
			 
			 return ps.executeUpdate();
			 
			} catch (Exception e) {
			 e.printStackTrace();
			} finally{
			 // sql connector 해제
			 try{
			     if(conn!=null) conn.close();
			     if(ps!=null) ps.close();
			 } catch (Exception e) {
			     e.printStackTrace();
			 }
			}
			return -1;    // DB 오류
			} 


	public int LoginCheck(String userID, String userPassword) {
		// TODO Auto-generated method stub
		
		String sql = "select userPassword from users where userID = ?";
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, userID);
			rs = ps.executeQuery();
			if(rs.next()) {
				if (rs.getString(1).equals(userPassword)) 
					return 1;//아디비번맞으면
				else
					return 0; //틀리면
			}
			return -1;//아이디없으면
			
	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
				if(rs != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		 return -2;
	}
	
}