package de.budget.project.services.impl;

import de.budget.project.model.categoryType.CategoryType;
import de.budget.project.model.transaction.Transaction;
import de.budget.project.repository.TransactionRepository;
import de.budget.project.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void insertTransaction(Date customDate,
                                  String amount,
                                  String currency,
                                  Long walletId,
                                  Long categoryId,
                                  String description) {
        transactionRepository.insertTransaction(customDate, new BigDecimal(amount), currency, walletId, categoryId, description, new Date(), new Date());
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return transactionRepository.getTransactionById(id);
    }

    @Override
    public List<Transaction> getTransactionsByWalletId(Long walletId) {
        return transactionRepository.getTransactionsByWalletId(walletId);
    }



    @Override
    public BigDecimal recalculateBalance(Long walletId) {
        List<Transaction> allTransactions = transactionRepository.getTransactionsByWalletId(walletId);
        BigDecimal debitSum = allTransactions
                .stream()
                .filter(p -> p.getCategory()
                        .getCategoryType()
                        .equals(CategoryType.DEBIT))
                .map(t-> t.getAmount().getAmount())
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal(0));

        BigDecimal creditSum = allTransactions
                .stream()
                .filter(p -> p.getCategory()
                        .getCategoryType()
                        .equals(CategoryType.CREDIT))
                .map(t-> t.getAmount().getAmount())
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal(0));
        return debitSum.add(creditSum);
    }
}