package com.sakib.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.sakib.bank.model.enums.AccountStatus;
import com.sakib.bank.model.enums.AccountType;

public class Account {

	private Long accountId;
	private Long userId;
	private String accountNumber;
	private AccountType accountType;
	private BigDecimal balance;
	private AccountStatus status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	public Account() {
	}
	public Account(Long accountId, Long userId, String accountNumber, AccountType accountType, BigDecimal balance,
			AccountStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.accountId = accountId;
		this.userId = userId;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public AccountStatus getStatus() {
		return status;
	}
	public void setStatus(AccountStatus status) {
		this.status = status;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", userId=" + userId + ", accountNumber=" + accountNumber
				+ ", accountType=" + accountType + ", balance=" + balance + ", status=" + status + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	
	
}
