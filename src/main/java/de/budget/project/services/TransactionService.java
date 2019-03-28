package de.budget.project.services;

import de.budget.project.model.transaction.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransactionService {

    void insertTransaction(Date customDate,
                           String amount,
                           String currency,
                           Long walletId,
                           Long categoryId,
                           String description);

    Transaction getTransactionById(Long id);

    List<Transaction> getTransactionsByWalletId(Long walletId);

    BigDecimal recalculateBalance(Long walletId);
}