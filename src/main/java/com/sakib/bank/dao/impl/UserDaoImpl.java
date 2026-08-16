package com.sakib.bank.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sakib.bank.dao.UserDao;
import com.sakib.bank.model.User;
import com.sakib.bank.model.enums.UserRole;
import com.sakib.bank.model.enums.UserStatus;
import com.sakib.bank.util.DBConnectionUtil;

public class UserDaoImpl implements UserDao{

	@Override
	public boolean saveUser(User user) throws SQLException {
		if(user == null) return false;
		
		String sql= "INSERT INTO users " +
                "(user_id, full_name, email, phone_no, password_hash, role, status, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try(Connection con = DBConnectionUtil.getConnection();
				PreparedStatement ps= con.prepareStatement(sql)){
			
			ps.setLong(1, user.getUserId());
			ps.setString(2, user.getFullName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPhoneNo());
			ps.setString(5, user.getPasswordHash());
			ps.setString(6, user.getRole().name());
			ps.setString(7, user.getStatus().name());
			ps.setTimestamp(8, Timestamp.valueOf(user.getCreatedAt()));
			ps.setTimestamp(9, Timestamp.valueOf(user.getUpdatedAt()));
			
			return ps.executeUpdate()>0;
		}
	}

	@Override
	public Optional<User> findById(Long userId) throws SQLException {
		
		if(userId== null || userId < 0) return Optional.empty();
		
		String sql= "SELECT * FROM users WHERE user_id= ?";
		try(Connection con = DBConnectionUtil.getConnection();
				PreparedStatement ps= con.prepareStatement(sql)){
			ps.setLong(1, userId);
			
			try(ResultSet rs= ps.executeQuery()){
				if(rs.next())
				{
					return Optional.of(mapUser(rs));
				}
			}
		}
		return Optional.empty();
	}
	
	private User mapUser(ResultSet rs) throws SQLException{
		User user= new User();
		
			user.setUserId(rs.getLong("user_id"));
			user.setFullName(rs.getString("full_name"));
			user.setEmail(rs.getString("email"));
			user.setPhoneNo(rs.getString("phone_no"));
			user.setPasswordHash(rs.getString("password_hash"));
			user.setRole(UserRole.valueOf(rs.getString("role")));
			user.setStatus(UserStatus.valueOf(rs.getString("status")));
			user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
			
			Timestamp updatedTs= rs.getTimestamp("updated_at");
			if(updatedTs != null) {
				user.setUpdatedAt(updatedTs.toLocalDateTime());
			}

		return user;
	}

	@Override
	public Optional<User> findByEmail(String email) throws SQLException {
		
		if(email == null) return Optional.empty();
		String sql= "SELECT * FROM users WHERE email= ?";
		try(Connection con = DBConnectionUtil.getConnection();
				PreparedStatement ps= con.prepareStatement(sql)){
			ps.setString(1, email);
			try(ResultSet rs= ps.executeQuery()){
				
				if(rs.next()) {
					return Optional.of(mapUser(rs));
				}
				
			}
		}
		return Optional.empty();
	}

	@Override
	public List<User> findAllUsers() throws SQLException {
		List<User> list= new ArrayList<User>();
		String sql= "SELECT * FROM users";
		
		try(Connection con = DBConnectionUtil.getConnection();
				PreparedStatement ps= con.prepareStatement(sql);
				ResultSet rs= ps.executeQuery()){

				while(rs.next()) {
					list.add(mapUser(rs));
			}
		}
		
		return list;
	}

	@Override
	public boolean updateUser(User user) throws SQLException {
		
		if(user == null) return false;
		
		String sql= "UPDATE users "
				+ "SET full_name= ? ,"
				+ "email = ?,"
				+"phone_no = ?,"
				+"password_hash = ?,"
				+"role = ?,"
				+"status = ?,"
				+"updated_at = ? "
				+ "WHERE user_id= ?";
		
		try(Connection con= DBConnectionUtil.getConnection();
				PreparedStatement ps= con.prepareStatement(sql)){
			
			ps.setString(1, user.getFullName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPhoneNo());
			ps.setString(4, user.getPasswordHash());
			ps.setString(5, user.getRole().name());
			ps.setString(6, user.getStatus().name());
			ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
			ps.setLong(8, user.getUserId());
		
			return ps.executeUpdate()>0;
		}
	}

	@Override
	public boolean deleteUser(Long userId) throws SQLException {
		
		if(userId== null) return false;
		
		String sql= "DELETE FROM users WHERE user_id = ?";
		
		try(Connection con= DBConnectionUtil.getConnection();
				PreparedStatement ps= con.prepareStatement(sql)){
			ps.setLong(1, userId);
			return ps.executeUpdate()>0;
		}
	}

	@Override
	public boolean updateUserStatus(Long userId, UserStatus status) throws SQLException {
		
		if(userId == null || userId <=0 || status == null) return false;
		
		String sql= "UPDATE users SET status = ?, updated_at = ? WHERE user_id = ?";
		
		try(Connection con = DBConnectionUtil.getConnection();
				PreparedStatement ps= con.prepareStatement(sql)){
			
			ps.setString(1, status.name());
			ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			ps.setLong(3, userId);
			
			return ps.executeUpdate()>0;
		}
	}

}
