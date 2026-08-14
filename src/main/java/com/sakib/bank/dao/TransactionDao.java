package com.sakib.bank.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.sakib.bank.model.Transaction;

public interface TransactionDao {

    boolean saveTransaction(Transaction transaction) throws SQLException;

    Optional<Transaction> findByTransactionId(Long transactionId) throws SQLException;

    Optional<Transaction> findByReferenceNo(String referenceNo) throws SQLException;

    List<Transaction> findAllTransactionsByAccountId(Long accountId) throws SQLException;

    List<Transaction> findTransactionsBetweenDates(
            Long accountId,
            LocalDateTime startDate,
            LocalDateTime endDate) throws SQLException;

    boolean deleteTransaction(Long transactionId) throws SQLException;
}