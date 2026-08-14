package com.sakib.bank.util;

import java.sql.Connection;

public class DBConnectionTest {
	public static void main(String[] args) {
		
		Connection con = DBConnectionUtil.getConnection();
		
	}
}
