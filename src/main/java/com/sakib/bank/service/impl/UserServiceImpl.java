package com.sakib.bank.service.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.sakib.bank.dao.impl.UserDaoImpl;
import com.sakib.bank.model.User;
import com.sakib.bank.model.enums.UserStatus;
import com.sakib.bank.service.UserService;


public class UserServiceImpl implements UserService {

	private UserDaoImpl userDao= new UserDaoImpl();
	
	@Override
	public boolean registerUser(User user) throws SQLException {

	    if (!isValidUser(user)) {
	        return false;
	    }

	    return userDao.saveUser(user);
	}
	
	private boolean isValidUser(User user) {

	    if (user == null) {
	        return false;
	    }

	    if (user.getUserId() == null ||
	    		user.getFullName() == null ||
	        user.getEmail() == null ||
	        user.getPasswordHash() == null ||
	        user.getPhoneNo() == null ||
	        user.getRole() == null ||
	        user.getStatus() == null) {
	        return false;
	    }

	    String fullName = user.getFullName().trim();
	    String email = user.getEmail().trim();
	    String phoneNo = user.getPhoneNo().trim();
	    String password = user.getPasswordHash();

	    // Full name
	    if (fullName.length() < 2 ||
	        fullName.length() > 100 ||
	        !fullName.matches("^[A-Za-z]+(?:[ .'-][A-Za-z]+)*$")) {
	        return false;
	    }

	    // Email
	    if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
	        return false;
	    }

	    // Phone number
	    if (!phoneNo.matches("^[6-9]\\d{9}$")) {
	        return false;
	    }

	    // Password
	    if (password.length() < 8 ||
	        password.length() > 64 ||
	        !password.matches(".*[A-Z].*") ||
	        !password.matches(".*[a-z].*") ||
	        !password.matches(".*\\d.*") ||
	        !password.matches(".*[^A-Za-z0-9].*")) {
	        return false;
	    }

	    return true;
	}
	
	@Override
	public Optional<User> findUserById(Long userId) throws SQLException {
		 
		if (userId == null || userId <= 0) {
		        return Optional.empty();
		    }
		return userDao.findById(userId);
	}

	@Override
	public Optional<User> findUserByEmail(String email) throws SQLException {
		email = email.trim();
		if(email == null || email.isBlank()) {
			return Optional.empty();
		}
		return userDao.findByEmail(email);
	}

	@Override
	public List<User> findAllUsers() throws SQLException {
		return userDao.findAllUsers();
	}

	@Override
	public boolean updateUser(User user) throws SQLException {
		
		Optional<User> optionalUser= userDao.findById(user.getUserId());
		if(optionalUser.isEmpty()) {
			return false;
		}
		User foundUser = optionalUser.get();
		
		if(foundUser == null)
		{
			return false;
		}
		// Full Name
		if(!foundUser.getFullName().equals(user.getFullName())) {
			String fullName = user.getFullName().trim();
			 if (fullName.length() < 2 ||
				        fullName.length() > 100 ||
				        !fullName.matches("^[A-Za-z]+(?:[ .'-][A-Za-z]+)*$")) {
				        return false;
				    }
		}
		// Email
		if(!foundUser.getEmail().equals(user.getEmail())) {
			String email = user.getEmail().trim();
			if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
		        return false;
		    }
		}
		// Phone changed
	    if (!foundUser.getPhoneNo().equals(user.getPhoneNo())) {

	        String phoneNo = user.getPhoneNo().trim();

	        if (!phoneNo.matches("^[6-9]\\d{9}$")) {
	            return false;
	        }
	    }
		return userDao.updateUser(user);
	}

	@Override
	public boolean deleteUser(Long userId) throws SQLException {
		if(userId == null || userId <=0)return false;
		
		return userDao.deleteUser(userId);
	}

	@Override
	public boolean updateUserStatus(Long userId, UserStatus status) throws SQLException {
		if(userId == null || userId <=0 || status == null)
			return false;
		return userDao.updateUserStatus(userId, status);
	}

}
