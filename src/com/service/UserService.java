package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.UserModel;

public class UserService {

	private Connection con;
	
	public UserService(Connection con) {
		this.con=con;
	}
	public boolean userRegistration(UserModel user) {
		boolean res=false;
		try {
			PreparedStatement ps=con.prepareStatement("insert into user_table(fullname, email, phone_no, password, userRole) values(?, ?, ?, ?, ?)");
			ps.setString(1, user.getFullName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPhoneNo());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getUserRole());
			
			int r=ps.executeUpdate();
			if(r>0) {
				System.out.println("Registration success !");
				res=true;
			}
			else {
				System.out.println("Registration failed !");
				res=false;
			}
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return res;
	}
	
	public UserModel userLogin(String email, String password) {
		UserModel user=null;
		try {
			PreparedStatement ps=con.prepareStatement("select * from user_table where email=? and password=?");
			ps.setString(1,  email);
			ps.setString(2, password);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				user=new UserModel();
				user.setUserId(rs.getInt(1));
				user.setFullName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPhoneNo(rs.getString(4));
				user.setUserRole(rs.getString(6));
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return user;
	}
}
