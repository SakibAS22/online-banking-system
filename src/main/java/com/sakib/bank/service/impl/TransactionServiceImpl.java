package com.sakib.bank.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.sakib.bank.dao.impl.TransactionDaoImpl;
import com.sakib.bank.model.Transaction;
import com.sakib.bank.service.TransactionService;

public class TransactionServiceImpl implements TransactionService{
	
	private TransactionDaoImpl transactionDao= new TransactionDaoImpl();
	
	@Override
	public boolean saveTransaction(Transaction transaction) throws SQLException {
		if(transaction == null ||
				transaction.getTransactionId() == null  ||
				transaction.getTransactionType() == null ||
				transaction.getAmount() == null ||
				transaction.getReferenceNo() == null ||
				transaction.getBalanceAfterTransaction() == null ||
				transaction.getTransactionAt() == null) {
			return false;
		}
		if(transaction.getTransactionId() <=0 || transaction.getReferenceNo().trim().isBlank()) {
			return false;
		}
		if (transaction.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
		    return false;
		}

		if (transaction.getBalanceAfterTransaction().compareTo(BigDecimal.ZERO) < 0) {
		    return false;
		}
		
		return transactionDao.saveTransaction(transaction);
	}

	@Override
	public Optional<Transaction> findByTransactionId(Long transactionId) throws SQLException {
		if(transactionId == null || transactionId <=0) {
			return Optional.empty();
		}
		return transactionDao.findByTransactionId(transactionId);
	}

	@Override
	public Optional<Transaction> findByReferenceNo(String referenceNo) throws SQLException {
		if(referenceNo == null || referenceNo.trim().isBlank()) {
			return Optional.empty();
		}
		return transactionDao.findByReferenceNo(referenceNo.trim());
	}

	@Override
	public List<Transaction> findAllTransactionsByAccountId(Long accountId) throws SQLException {
		if(accountId == null || accountId <= 0) {
			return Collections.emptyList();
		}
		return transactionDao.findAllTransactionsByAccountId(accountId);
	}

	@Override
	public List<Transaction> findTransactionsBetweenDates(Long accountId, LocalDateTime startDate,
			LocalDateTime endDate) throws SQLException {
		if(accountId == null || accountId <= 0) {
			return Collections.emptyList();
		}
		
		return null;
	}
	
}
