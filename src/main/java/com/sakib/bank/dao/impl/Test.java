package com.sakib.bank.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.sakib.bank.model.Account;

public class Test {

    public static void main(String[] args) throws SQLException {

        AccountDaoImpl accountDao = new AccountDaoImpl();

        Long accountId = 1001L;

        // Check current balance
        System.out.println("Before update:");

        accountDao.findAccountById(accountId)
                .ifPresentOrElse(
                        account -> System.out.println(account),
                        () -> System.out.println("Account not found")
                );

        // Update balance
        BigDecimal newBalance = new BigDecimal("7500.00");

        boolean updated = accountDao.updateBalance(accountId, newBalance);

        System.out.println("\nBalance update successful: " + updated);

        // Check balance after update
        System.out.println("\nAfter update:");

        accountDao.findAccountById(accountId)
                .ifPresentOrElse(
                        account -> System.out.println(account),
                        () -> System.out.println("Account not found")
                );
    }
}