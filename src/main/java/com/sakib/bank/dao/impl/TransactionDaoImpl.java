package com.sakib.bank.dao.impl;

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

import com.sakib.bank.dao.TransactionDao;
import com.sakib.bank.model.Transaction;
import com.sakib.bank.model.enums.TransactionType;
import com.sakib.bank.util.DBConnectionUtil;

public class TransactionDaoImpl implements TransactionDao{

	@Override
	public boolean saveTransaction(Transaction transaction) throws SQLException {
		
		if(transaction == null || transaction.getTransactionId() == null ||
				transaction.getTransactionId() <=0 ) return false;
		
		String sql= "INSERT INTO transactions (transaction_id, account_id, "
				+ "transaction_type, amount, description, reference_no, "
				+ "balance_after_transaction, transaction_at) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try(Connection con = DBConnectionUtil.getConnection();
				PreparedStatement ps= con.prepareStatement(sql)){
			ps.setLong(1, transaction.getTransactionId());
			ps.setLong(2, transaction.getAccountId());
			ps.setString(3, transaction.getTransactionType().name());
			ps.setBigDecimal(4, transaction.getAmount());
			ps.setString(5, transaction.getDescription());
			ps.setString(6, transaction.getReferenceNo());
			ps.setBigDecimal(7, transaction.getBalanceAfterTransaction());
			ps.setTimestamp(8, Timestamp.valueOf(transaction.getTransactionAt()));
			
			return ps.executeUpdate()>0;
		}
	}

	@Override
	public Optional<Transaction> findByTransactionId(Long transactionId) throws SQLException {
		
		 if (transactionId == null || transactionId <= 0) {
		        return Optional.empty();
		    }

		    String sql = "SELECT * FROM transactions WHERE transaction_id = ?";

		    try (Connection con = DBConnectionUtil.getConnection();
		         PreparedStatement ps = con.prepareStatement(sql)) {

		        ps.setLong(1, transactionId);

		        try (ResultSet rs = ps.executeQuery()) {

		            if (rs.next()) {
		                return Optional.of(mapTransaction(rs));
		            }
		        }
		    }

		    return Optional.empty();
	}

	private Transaction mapTransaction(ResultSet rs) throws SQLException {

	    Transaction transaction = new Transaction();

	    transaction.setTransactionId(rs.getLong("transaction_id"));
	    transaction.setAccountId(rs.getLong("account_id"));
	    transaction.setTransactionType(
	            TransactionType.valueOf(rs.getString("transaction_type")));
	    transaction.setAmount(rs.getBigDecimal("amount"));
	    transaction.setDescription(rs.getString("description"));
	    transaction.setReferenceNo(rs.getString("reference_no"));
	    transaction.setBalanceAfterTransaction(
	            rs.getBigDecimal("balance_after_transaction"));
	    transaction.setTransactionAt(
	            rs.getTimestamp("transaction_at").toLocalDateTime());

	    return transaction;
	}
	
	@Override
	public Optional<Transaction> findByReferenceNo(String referenceNo) throws SQLException {
		
		if (referenceNo == null || referenceNo.isBlank()) {
	        return Optional.empty();
	    }

	    String sql = "SELECT * FROM transactions WHERE reference_no = ?";

	    try (Connection con = DBConnectionUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, referenceNo);

	        try (ResultSet rs = ps.executeQuery()) {

	            if (rs.next()) {
	                return Optional.of(mapTransaction(rs));
	            }
	        }
	    }

	    return Optional.empty();
	}

	@Override
	public List<Transaction> findAllTransactionsByAccountId(Long accountId) throws SQLException {
		
		List<Transaction> list = new ArrayList<>();

	    if (accountId == null || accountId <= 0) {
	        return list;
	    }

	    String sql = "SELECT * FROM transactions "
	               + "WHERE account_id = ? "
	               + "ORDER BY transaction_at DESC";

	    try (Connection con = DBConnectionUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setLong(1, accountId);

	        try (ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                list.add(mapTransaction(rs));
	            }
	        }
	    }

	    return list;
	}

	@Override
	public List<Transaction> findTransactionsBetweenDates(Long accountId, LocalDateTime startDate,
			LocalDateTime endDate) throws SQLException {
		
		List<Transaction> list = new ArrayList<>();

	    if (accountId == null || accountId <= 0 ||
	        startDate == null || endDate == null ||
	        startDate.isAfter(endDate)) {
	        return list;
	    }

	    String sql = "SELECT * FROM transactions "
	               + "WHERE account_id = ? "
	               + "AND transaction_at BETWEEN ? AND ? "
	               + "ORDER BY transaction_at DESC";

	    try (Connection con = DBConnectionUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setLong(1, accountId);
	        ps.setTimestamp(2, Timestamp.valueOf(startDate));
	        ps.setTimestamp(3, Timestamp.valueOf(endDate));

	        try (ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                list.add(mapTransaction(rs));
	            }
	        }
	    }
	    return list;
	}

}
