package com.sakib.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.sakib.bank.dao.UserDao;
import com.sakib.bank.model.User;
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
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<User> findByEmail(String email) throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<User> findAllUsers() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(Long userId) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserStatus(Long userId, UserStatus status) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
