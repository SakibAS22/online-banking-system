package com.sakib.bank.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.sakib.bank.model.Account;
import com.sakib.bank.model.enums.AccountStatus;

public interface AccountDao {
	
	public boolean createAccount(Account account) throws SQLException;
	
	public Optional<Account> findAccountById(Long accountId) throws SQLException;
	
	public Optional<Account> findAccountByAccountNumber(String accountNumber) throws SQLException;
	
	public List<Account> findAllAccountOfUser(Long userId) throws SQLException;
	
	public boolean updateAccount(Account account) throws SQLException;
	
	public boolean updateBalance(Long accountId, BigDecimal balance) throws SQLException;
	
	public boolean deleteAccount(Long accountId) throws SQLException;

	public boolean changeAccountStatus(Long accountId, AccountStatus accountStatus) throws SQLException;
}
