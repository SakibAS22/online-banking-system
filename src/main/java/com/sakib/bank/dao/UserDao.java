
package com.sakib.bank.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.sakib.bank.model.User;
import com.sakib.bank.model.enums.UserStatus;

public interface UserDao {
	public boolean saveUser(User user) throws SQLException;
	public Optional<User> findById(Long userId)throws SQLException;
	public Optional<User> findByEmail(String email)throws SQLException;
	public List<User> findAllUsers()throws SQLException;
	public boolean updateUser(User user)throws SQLException;
	public boolean deleteUser(Long userId)throws SQLException;
	public boolean updateUserStatus(Long userId, UserStatus status)throws SQLException;
	
}

