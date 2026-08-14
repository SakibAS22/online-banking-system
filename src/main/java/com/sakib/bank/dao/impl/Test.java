package com.sakib.bank.dao.impl;

import java.sql.SQLException;
import java.time.LocalDateTime;

import com.sakib.bank.model.User;
import com.sakib.bank.model.enums.UserRole;
import com.sakib.bank.model.enums.UserStatus;

public class Test {

	public static void main(String[] args) {
		
		User user= new User(453L, "Sakib A.Sankeshwarkar","sakibsankeshwarkar@gmail.com",
					"7057453408","Sakib@999",UserRole.ADMIN, UserStatus.APPROVED,LocalDateTime.now(), LocalDateTime.now());
		
		UserDaoImpl usd= new UserDaoImpl();
		try {
			usd.saveUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
