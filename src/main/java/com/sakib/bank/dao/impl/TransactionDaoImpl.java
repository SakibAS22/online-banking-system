package com.sakib.bank.dao.impl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.sakib.bank.dao.TransactionDao;
import com.sakib.bank.model.Transaction;

public class TransactionDaoImpl implements TransactionDao{

	@Override
	public boolean saveTransaction(Transaction transaction) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Transaction> findByTransactionId(Long transactionId) throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Transaction> findByReferenceNo(String referenceNo) throws SQLException {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Transaction> findAllTransactionsByAccountId(Long accountId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> findTransactionsBetweenDates(Long accountId, LocalDateTime startDate,
			LocalDateTime endDate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteTransaction(Long transactionId) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
