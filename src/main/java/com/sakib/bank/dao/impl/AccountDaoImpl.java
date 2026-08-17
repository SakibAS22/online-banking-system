package com.sakib.bank.dao.impl;

import java.io.ObjectInputFilter.Status;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.tags.shaded.org.apache.regexp.recompile;
import com.sakib.bank.dao.AccountDao;
import com.sakib.bank.model.Account;
import com.sakib.bank.model.enums.AccountStatus;
import com.sakib.bank.model.enums.AccountType;
import com.sakib.bank.util.DBConnectionUtil;

public class AccountDaoImpl implements AccountDao{

	@Override
	public boolean createAccount(Account account) throws SQLException {
		
		if(account == null) return false;
		
		String sql= "INSERT INTO accounts"
				+ "(account_id,user_id, account_number, account_type, balance, "
				+ "status ,created_at, updated_at)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		try(Connection con = DBConnectionUtil.getConnection();
				PreparedStatement ps= con.prepareStatement(sql)){
			ps.setLong(1, account.getAccountId());
			ps.setLong(2, account.getUserId());
			ps.setString(3, account.getAccountNumber());
			ps.setString(4, account.getAccountType().name());
			ps.setBigDecimal(5, account.getBalance());
			ps.setString(6, account.getStatus().name());
			ps.setTimestamp(7, Timestamp.valueOf(account.getCreatedAt()));
			ps.setTimestamp(8, Timestamp.valueOf(account.getUpdatedAt()));
			
			return ps.executeUpdate()>0;
		}
	}

	@Override
	public Optional<Account> findAccountById(Long accountId) throws SQLException {
		
		if(accountId == null || accountId <= 0) return Optional.empty();
		String sql= "SELECT * FROM accounts WHERE account_id = ?";
		
		try(Connection con= DBConnectionUtil.getConnection();
				PreparedStatement ps= con.prepareStatement(sql)){
			ps.setLong(1, accountId);
			
			try(ResultSet rs= ps.executeQuery()){
				if(rs.next()) {
					return Optional.of(mapAccount(rs));
				}
			}
		}
		
		
		return Optional.empty();
	}
	
	private Account mapAccount(ResultSet rs)throws SQLException {
		Account account = new Account();
		
		account.setAccountId(rs.getLong("account_id"));
		account.setUserId(rs.getLong("user_id"));
		account.setAccountNumber(rs.getString("account_number"));
		account.setAccountType(AccountType.valueOf(rs.getString("account_type")));
		account.setBalance(rs.getBigDecimal("balance"));
		account.setStatus(AccountStatus.valueOf(rs.getString("status")));
		account.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
		
		Timestamp updatedTs= rs.getTimestamp("updated_at");
		
		if(updatedTs != null) {
			account.setUpdatedAt(updatedTs.toLocalDateTime());
		}
		return account;
	}

	@Override
	public Optional<Account> findAccountByAccountNumber(String accountNumber) throws SQLException {
		if(accountNumber == null) return Optional.empty();
		
		String sql= "SELECT * FROM accounts WHERE account_number= ?";
		
		try(Connection con = DBConnectionUtil.getConnection();
				PreparedStatement ps= con.prepareStatement(sql)){
			
			ps.setString(1, accountNumber);
			try(ResultSet rs= ps.executeQuery()){
				if(rs.next()) {
					return Optional.of(mapAccount(rs));
				}
			}
		}
		return Optional.empty();
	}

	@Override
	public List<Account> findAllAccountOfUser(Long userId) throws SQLException {
		
		List<Account> list = new ArrayList<Account>();
		
		if(userId == null || userId <=0) return list;
		String sql= "SELECT * FROM accounts WHERE user_id = ?";
		
		try(Connection con= DBConnectionUtil.getConnection();
				PreparedStatement ps= con.prepareStatement(sql)){
			ps.setLong(1, userId);
			
			try(ResultSet rs= ps.executeQuery()){
				while(rs.next()) {
					list.add(mapAccount(rs));
				}
			}
		}
		return list;
	}

	@Override
	public boolean updateAccount(Account account) throws SQLException {
		
		if(account == null || account.getAccountId() == null ||
				account.getAccountId() <= 0) return false;
		
		String sql= "UPDATE accounts "
				+ "SET account_type = ?, "
				+ "status = ? ,"
				+ "updated_at = ? "
				+ "WHERE account_id= ?";
		try(Connection con = DBConnectionUtil.getConnection();
				PreparedStatement ps= con.prepareStatement(sql)){
			ps.setString(1, account.getAccountType().name());
			ps.setString(2, account.getStatus().name());
			ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
			ps.setLong(4, account.getAccountId());
			
			return ps.executeUpdate()>0;
		}
	}

	@Override
	public boolean updateBalance(Long accountId, BigDecimal balance) throws SQLException {
		
		if(accountId == null || accountId <= 0 || balance == null) {
			return false;
		}
		String sql = "UPDATE accounts "
				+ "SET balance = ?, "
				+ "updated_at = ? "
				+ "WHERE account_id= ?";
		
		try(Connection con = DBConnectionUtil.getConnection();
				PreparedStatement ps= con.prepareStatement(sql)){
			ps.setBigDecimal(1, balance);
			ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			ps.setLong(3, accountId);
			
			return ps.executeUpdate() >0;
		}
	}

	@Override
	public boolean deleteAccount(Long accountId) throws SQLException {
		if(accountId == null || accountId <=0) return false;
		
		String sql= "DELETE FROM accounts WHERE account_id = ?";
		
		try(Connection con = DBConnectionUtil.getConnection();
				PreparedStatement ps= con.prepareStatement(sql)){
			ps.setLong(1, accountId);
			
			return ps.executeUpdate()>0;
		}
	}

	@Override
	public boolean changeAccountStatus(Long accountId, AccountStatus accountStatus) throws SQLException {
		
		if(accountId == null || accountId <=0 || accountStatus.name() == null) return false;
		
		String sql= "UPDATE accounts "
				+ "SET status = ? "
				+ "WHERE account_id = ?";
		
		try(Connection con= DBConnectionUtil.getConnection();
				PreparedStatement ps= con.prepareStatement(sql)){
			ps.setString(1, accountStatus.name());
			ps.setLong(2, accountId);
			
			return ps.executeUpdate()>0;
		}
	}

}
