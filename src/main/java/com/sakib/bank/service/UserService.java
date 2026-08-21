package com.sakib.bank.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.sakib.bank.model.User;
import com.sakib.bank.model.enums.UserStatus;

public interface UserService {
	
	boolean registerUser(User user) throws SQLException;
	
	Optional<User> findUserById(Long userId) throws SQLException;
	
	Optional<User> findUserByEmail(String email) throws SQLException;
	
	List<User> findAllUsers() throws SQLException;
	
	boolean updateUser(User user)throws SQLException; 
	
	boolean deleteUser(Long userId) throws SQLException;
	
	boolean updateUserStatus(Long userId, UserStatus status) throws SQLException;
}
