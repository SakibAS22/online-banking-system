package com.sakib.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.sakib.bank.model.enums.TransactionType;

public class Transaction {
	
	private Long transactionId;
	private Long accountId;
	private TransactionType transactionType;
	private BigDecimal amount;
	private String description;
	private String referenceNo;
	private BigDecimal balanceAfterTransaction;
	private LocalDateTime transactionAt;
	public Transaction() {
	}
	public Transaction(Long transactionId, Long accountId, TransactionType transactionType, BigDecimal amount,
			String description, String referenceNo, BigDecimal balanceAfterTransaction, LocalDateTime transactionAt) {
		super();
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.transactionType = transactionType;
		this.amount = amount;
		this.description = description;
		this.referenceNo = referenceNo;
		this.balanceAfterTransaction = balanceAfterTransaction;
		this.transactionAt = transactionAt;
	}
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public BigDecimal getBalanceAfterTransaction() {
		return balanceAfterTransaction;
	}
	public void setBalanceAfterTransaction(BigDecimal balanceAfterTransaction) {
		this.balanceAfterTransaction = balanceAfterTransaction;
	}
	public LocalDateTime getTransactionAt() {
		return transactionAt;
	}
	public void setTransactionAt(LocalDateTime transactionAt) {
		this.transactionAt = transactionAt;
	}
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", accountId=" + accountId + ", transactionType="
				+ transactionType + ", amount=" + amount + ", description=" + description + ", referenceNo=" + referenceNo
				+ ", balanceAfterTransaction=" + balanceAfterTransaction + ", transactionAt=" + transactionAt + "]";
	}
	
	
	
	
	
	
}
