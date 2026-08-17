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

import com.sakib.bank.dao.TransferDao;
import com.sakib.bank.model.Transfer;
import com.sakib.bank.model.enums.TransferStatus;
import com.sakib.bank.util.DBConnectionUtil;

public class TransferDaoImpl implements TransferDao {

	@Override
	public boolean saveTransfer(Transfer transfer) throws SQLException {
		if (transfer == null ||
		        transfer.getTransferId() == null ||
		        transfer.getTransferId() <= 0) {
		        return false;
		    }

		    String sql = "INSERT INTO transfers "
		            + "(transfer_id, from_account_id, to_account_id, amount, "
		            + "status, created_at, updated_at) "
		            + "VALUES (?, ?, ?, ?, ?, ?, ?)";

		    try (Connection con = DBConnectionUtil.getConnection();
		         PreparedStatement ps = con.prepareStatement(sql)) {

		        ps.setLong(1, transfer.getTransferId());
		        ps.setLong(2, transfer.getFromAccountId());
		        ps.setLong(3, transfer.getToAccountId());
		        ps.setBigDecimal(4, transfer.getAmount());
		        ps.setString(5, transfer.getStatus().name());
		        ps.setTimestamp(6, Timestamp.valueOf(transfer.getCreatedAt()));

		        if (transfer.getUpdatedAt() != null) {
		            ps.setTimestamp(7, Timestamp.valueOf(transfer.getUpdatedAt()));
		        } else {
		            ps.setNull(7, java.sql.Types.TIMESTAMP);
		        }

		        return ps.executeUpdate() > 0;
		    }
	}

	@Override
	public Optional<Transfer> findTransferById(Long transferId) throws SQLException {
		if (transferId == null || transferId <= 0) {
            return Optional.empty();
        }

        String sql = "SELECT * FROM transfers WHERE transfer_id = ?";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, transferId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(mapTransfer(rs));
                }
            }
        }

        return Optional.empty();
	}

	@Override
	public List<Transfer> findTransfersByFromAccount(Long accountId) throws SQLException {
		List<Transfer> list = new ArrayList<>();

        if (accountId == null || accountId <= 0) {
            return list;
        }

        String sql = "SELECT * FROM transfers "
                + "WHERE from_account_id = ? "
                + "ORDER BY created_at DESC";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, accountId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    list.add(mapTransfer(rs));
                }
            }
        }

        return list;
	}

	@Override
	public List<Transfer> findTransfersByToAccount(Long accountId) throws SQLException {
		List<Transfer> list = new ArrayList<>();

        if (accountId == null || accountId <= 0) {
            return list;
        }

        String sql = "SELECT * FROM transfers "
                + "WHERE to_account_id = ? "
                + "ORDER BY created_at DESC";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, accountId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    list.add(mapTransfer(rs));
                }
            }
        }

        return list;
	}

	@Override
	public List<Transfer> findAllTransfersByAccount(Long accountId)
	        throws SQLException {

	    List<Transfer> list = new ArrayList<>();

	    if (accountId == null || accountId <= 0) {
	        return list;
	    }

	    String sql = "SELECT * FROM transfers "
	            + "WHERE from_account_id = ? "
	            + "OR to_account_id = ? "
	            + "ORDER BY created_at DESC";

	    try (Connection con = DBConnectionUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setLong(1, accountId);
	        ps.setLong(2, accountId);

	        try (ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                list.add(mapTransfer(rs));
	            }
	        }
	    }

	    return list;
	}

	@Override
	public boolean updateTransferStatus(Long transferId, TransferStatus status) throws SQLException {
		if (transferId == null || transferId <= 0 ||
	            status == null) {
	            return false;
	        }

	        String sql = "UPDATE transfers "
	                + "SET status = ?, updated_at = ? "
	                + "WHERE transfer_id = ?";

	        try (Connection con = DBConnectionUtil.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setString(1, status.name());
	            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
	            ps.setLong(3, transferId);

	            return ps.executeUpdate() > 0;
	        }
	}

    private Transfer mapTransfer(ResultSet rs) throws SQLException {

        Transfer transfer = new Transfer();

        transfer.setTransferId(rs.getLong("transfer_id"));
        transfer.setFromAccountId(rs.getLong("from_account_id"));
        transfer.setToAccountId(rs.getLong("to_account_id"));
        transfer.setAmount(rs.getBigDecimal("amount"));
        transfer.setStatus(
                TransferStatus.valueOf(rs.getString("status"))
        );

        transfer.setCreatedAt(
                rs.getTimestamp("created_at").toLocalDateTime()
        );

        Timestamp updatedTs = rs.getTimestamp("updated_at");

        if (updatedTs != null) {
            transfer.setUpdatedAt(updatedTs.toLocalDateTime());
        }

        return transfer;
    }

}
