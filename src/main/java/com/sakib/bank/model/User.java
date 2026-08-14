package com.sakib.bank.model;

import java.time.LocalDateTime;

import com.sakib.bank.model.enums.UserRole;
import com.sakib.bank.model.enums.UserStatus;

public class User {
	private Long userId;
	private String fullName;
	private String email;
	private String phoneNo;
	private String passwordHash;
	private UserRole role;
	private UserStatus status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public User() {
	}

	public User(Long userId, String fullName, String email, String phoneNo, String passwordHash, UserRole role,
			UserStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.passwordHash = passwordHash;
		this.role = role;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
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
		// TODO Auto-generated method stub
		return "User{" +
	       "userId=" + userId +
	       ", fullName='" + fullName + '\'' +
	       ", email='" + email + '\'' +
	       ", phoneNo='" + phoneNo + '\'' +
	       ", role='" + role + '\'' +
	       ", status='" + status + '\'' +
	       ", createdAt=" + createdAt +
	       ", updatedAt=" + updatedAt +
	       '}';
	}
	
	
}
