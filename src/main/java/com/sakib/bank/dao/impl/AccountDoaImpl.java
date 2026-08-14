package com.sakib.bank.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.sakib.bank.dao.AccountDao;
import com.sakib.bank.model.Account;
import com.sakib.bank.model.enums.AccountStatus;

public class AccountDoaImpl implements AccountDao{

	@Override
	public boolean createAccount(Account account) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Account> findAccountById(Long accountId) throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Account> findAccountByAccountNumber(String accountNumber) throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Account> findAllAccountOfUser(Long userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateAccount(Account account) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBalance(Long accountId, BigDecimal balance) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAccount(Long accountId) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeAccountStatus(Long accountId, AccountStatus accountStatus) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
