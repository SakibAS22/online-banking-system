 package com.sakib.bank.service.impl;

import java.math.BigDecimal;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.eclipse.tags.shaded.org.apache.regexp.recompile;

import com.sakib.bank.dao.impl.AccountDaoImpl;
import com.sakib.bank.model.Account;
import com.sakib.bank.model.enums.AccountStatus;
import com.sakib.bank.service.AccountService;

public class AccountServiceImpl implements AccountService {

	private AccountDaoImpl accountDao= new AccountDaoImpl();
	@Override
	public boolean createAccount(Account account) throws SQLException {

	    if (!isValid(account)) {
	        return false;
	    }

	    return accountDao.createAccount(account);
	}

	private boolean isValid(Account account) {

	    if (account == null) {
	        return false;
	    }

	    if (account.getAccountId() == null ||
	    		account.getAccountNumber() == null ||
	        account.getAccountType() == null ||
	        account.getBalance() == null ||
	        account.getStatus() == null) {

	        return false;
	    }
	    
	    if(account.getAccountId() <=0) {
	    		return false;
	    }
	    String accountNumber = account.getAccountNumber();
	    BigDecimal balance = account.getBalance();

	    // Account number: 10-16 digits
	    if (!accountNumber.matches("\\d{10,16}")) {
	        return false;
	    }

	    // Balance cannot be negative
	    if (balance.compareTo(BigDecimal.ZERO) < 0) {
	        return false;
	    }

	    return true;
	}

	@Override
	public Optional<Account> findAccountById(Long accountId) throws SQLException {
		if(accountId == null || accountId <=0) {
			return Optional.empty();
		}
		return accountDao.findAccountById(accountId);
	}

	@Override
	public Optional<Account> findAccountByAccountNumber(String accountNumber) throws SQLException {
		if (accountNumber == null || accountNumber.trim().isBlank()) {
		    return Optional.empty();
		}

		return accountDao.findAccountByAccountNumber(accountNumber.trim());
	}

	@Override
	public List<Account> findAllAccountOfUser(Long userId) throws SQLException {
		if(userId == null || userId <=0) {
			return Collections.emptyList();
		}
		return accountDao.findAllAccountOfUser(userId);
	}

	@Override
	public boolean updateAccount(Account account) throws SQLException {
		if(account == null ||
				account.getAccountId() == null ||
				account.getAccountId() <=0) {
			return false;
		}
		
		Optional<Account> optionalAccount = findAccountById(account.getAccountId());
		if(optionalAccount.isEmpty()) {
			return false;
		}
		
		return accountDao.updateAccount(account);
	}

	@Override
	public boolean updateBalance(Long accountId, BigDecimal balance) throws SQLException {
		if(accountId == null || accountId <=0) {
			return false;
		}
		return accountDao.updateBalance(accountId, balance);
	}

	@Override
	public boolean deleteAccount(Long accountId) throws SQLException {
		if(accountId == null || accountId <=0) {
			return false;
		}
		return accountDao.deleteAccount(accountId);
	}

	@Override
	public boolean changeAccountStatus(Long accountId, AccountStatus accountStatus) throws SQLException {
		if(accountId == null || accountId <=0 || accountStatus == null) {
			return false;
		}
		
		return accountDao.changeAccountStatus(accountId, accountStatus);
	}

}
