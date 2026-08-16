package com.sakib.bank.dao.impl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

import com.sakib.bank.model.User;
import com.sakib.bank.model.enums.UserRole;
import com.sakib.bank.model.enums.UserStatus;

public class Test {

	public static void main(String[] args)throws SQLException {
		
		 UserDaoImpl userDao = new UserDaoImpl();

	        // First find the existing user
	        User user = userDao.findById(453L).orElse(null);

	        if (user == null) {
	            System.out.println("User not found");
	            return;
	        }

	        // Change some values
	        user.setFullName("Sakib Updated");
	        user.setPhoneNo("9876543210");

	        // Update database
	        boolean updated = userDao.updateUser(user);

	        System.out.println("Update successful: " + updated);

	        // Verify by reading from database again
	        User updatedUser = userDao.findById(453L).orElse(null);

	        System.out.println("Updated User:");
	        System.out.println(updatedUser);
}
}
