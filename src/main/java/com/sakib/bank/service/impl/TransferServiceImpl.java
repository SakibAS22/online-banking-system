package com.sakib.bank.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.temporal.TemporalAmount;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.sakib.bank.model.Transfer;
import com.sakib.bank.model.enums.TransferStatus;
import com.sakib.bank.service.TransferService;

public class TransferServiceImpl implements TransferService {

	private TransferService transferDao= new TransferServiceImpl();
	
	@Override
	public boolean saveTransfer(Transfer transfer) throws SQLException {
		if(transfer == null ||
				transfer.getTransferId() == null ||
				transfer.getFromAccountId() == null ||
				transfer.getToAccountId() == null ||
				transfer.getAmount() == null ||
				transfer.getStatus() == null ||
				transfer.getCreatedAt() == null ) {
			return false;
		}
		if(transfer.getTransferId() <=0 || transfer.getFromAccountId() <=0 ||
				transfer.getToAccountId() <=0) {
			return false;
		}
		if(transfer.getAmount().compareTo(BigDecimal.ZERO) <=0) {
			return false;
		}
		return transferDao.saveTransfer(transfer);
	}

	@Override
	public Optional<Transfer> findTransferById(Long transferId) throws SQLException {
		if(transferId == null ||
				transferId <=0) {
			return Optional.empty();
		}
		
		return transferDao.findTransferById(transferId);
	}

	@Override
	public List<Transfer> findTransfersByFromAccount(Long accountId) throws SQLException {
		if(accountId == null ||
				accountId <=0) {
			return Collections.emptyList();
		}
		return transferDao.findTransfersByFromAccount(accountId);
	}

	@Override
	public List<Transfer> findTransfersByToAccount(Long accountId) throws SQLException {
		if(accountId == null ||
				accountId <=0) {
			return Collections.emptyList();
		}
		return transferDao.findTransfersByToAccount(accountId);
	}

	@Override
	public List<Transfer> findAllTransfersByAccount(Long accountId) throws SQLException {
		if(accountId == null ||
				accountId <=0) {
			return Collections.emptyList();
		}
		return transferDao.findAllTransfersByAccount(accountId);
	}

	@Override
	public boolean updateTransferStatus(Long transferId, TransferStatus status) throws SQLException {
		if(transferId == null || transferId <=0 || status == null) {
			return false;
		}
		return transferDao.updateTransferStatus(transferId, status);
	}

}
