package com.sakib.bank.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.sakib.bank.model.Transfer;
import com.sakib.bank.model.enums.TransferStatus;

public interface TransferDao {

    boolean saveTransfer(Transfer transfer) throws SQLException;

    Optional<Transfer> findTransferById(Long transferId) throws SQLException;

    List<Transfer> findTransfersByFromAccount(Long accountId) throws SQLException;

    List<Transfer> findTransfersByToAccount(Long accountId) throws SQLException;

    public List<Transfer> findAllTransfersByAccount(Long accountId)throws SQLException;

    boolean updateTransferStatus(Long transferId, TransferStatus status) throws SQLException;
}