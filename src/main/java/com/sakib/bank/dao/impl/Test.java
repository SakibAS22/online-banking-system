package com.sakib.bank.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.sakib.bank.model.Transfer;
import com.sakib.bank.model.enums.TransferStatus;

public class Test {

    public static void main(String[] args) throws SQLException {

        TransferDaoImpl transferDao = new TransferDaoImpl();

        Long transferId = 3001L;
        Long fromAccountId = 1001L;
        Long toAccountId = 1002L;

        // ==========================================
        // 1. SAVE TRANSFER
        // ==========================================

        Transfer transfer = new Transfer();

        transfer.setTransferId(transferId);
        transfer.setFromAccountId(fromAccountId);
        transfer.setToAccountId(toAccountId);
        transfer.setAmount(new BigDecimal("1000.00"));
        transfer.setStatus(TransferStatus.PENDING);
        transfer.setCreatedAt(LocalDateTime.now());
        transfer.setUpdatedAt(null);

        boolean saved = transferDao.saveTransfer(transfer);

        System.out.println("1. Save Transfer: " + saved);


        // ==========================================
        // 2. FIND TRANSFER BY ID
        // ==========================================

        System.out.println("\n2. Find Transfer By ID:");

        transferDao.findTransferById(transferId)
                .ifPresentOrElse(
                        t -> System.out.println(t),
                        () -> System.out.println("Transfer not found")
                );


        // ==========================================
        // 3. FIND TRANSFERS FROM ACCOUNT
        // ==========================================

        System.out.println("\n3. Transfers From Account 1001:");

        List<Transfer> outgoing =
                transferDao.findTransfersByFromAccount(fromAccountId);

        if (outgoing.isEmpty()) {
            System.out.println("No outgoing transfers found");
        } else {
            for (Transfer t : outgoing) {
                System.out.println(t);
            }
        }


        // ==========================================
        // 4. FIND TRANSFERS TO ACCOUNT
        // ==========================================

        System.out.println("\n4. Transfers To Account 1002:");

        List<Transfer> incoming =
                transferDao.findTransfersByToAccount(toAccountId);

        if (incoming.isEmpty()) {
            System.out.println("No incoming transfers found");
        } else {
            for (Transfer t : incoming) {
                System.out.println(t);
            }
        }


        // ==========================================
        // 5. FIND ALL TRANSFERS OF ACCOUNT
        // ==========================================

        System.out.println("\n5. All Transfers Of Account 1001:");

        List<Transfer> allTransfers =
                transferDao.findAllTransfersByAccount(fromAccountId);

        if (allTransfers.isEmpty()) {
            System.out.println("No transfers found");
        } else {
            for (Transfer t : allTransfers) {
                System.out.println(t);
            }
        }


        // ==========================================
        // 6. UPDATE TRANSFER STATUS
        // ==========================================

        System.out.println("\n6. Update Transfer Status:");

        boolean statusUpdated =
                transferDao.updateTransferStatus(
                        transferId,
                        TransferStatus.COMPLETED
                );

        System.out.println(
                "Status update successful: " + statusUpdated
        );


        // ==========================================
        // 7. VERIFY UPDATED TRANSFER
        // ==========================================

        System.out.println("\n7. Transfer After Status Update:");

        transferDao.findTransferById(transferId)
                .ifPresentOrElse(
                        t -> System.out.println(t),
                        () -> System.out.println("Transfer not found")
                );
    }
}