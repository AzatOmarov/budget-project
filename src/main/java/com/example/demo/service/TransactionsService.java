package com.example.demo.service;

import com.example.demo.exception.TransactionNotFoundException;
import com.example.demo.model.Transaction;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionsService {


    @Autowired
    public TransactionRepository transactionRepository;

    public void addTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }

        //    Get all transactions
    public List<Transaction> getTransactions(){
        return transactionRepository.findAll();
    }

//    //    Get transaction by Id
    public Transaction getTransactionById(long id){
        Optional<Transaction> transactionById = transactionRepository.findById(id);
        if(!transactionById.isPresent()){
            throw new TransactionNotFoundException("transaction not found by id:" + id);
        }
        return transactionById.get();
    }

    public void deleteTransactionById(long id){
        transactionRepository.deleteById(id);
    }

    public List<Transaction> getTransactionsByCategory(String category){
        List<Transaction> listSortedByCategory = new LinkedList<>();
        for (Transaction transaction: transactionRepository.findAll()) {
            if(transaction.getCategory().equals(category)){
                listSortedByCategory.add(transaction);
            }
        }
        return listSortedByCategory;
    }

    public List<Transaction> getTransactionsBiggerThan(int minAmount){
        List<Transaction> list = new LinkedList<>();
        for (Transaction transaction: transactionRepository.findAll()) {
            if (transaction.getAmount() >= minAmount){
                list.add(transaction);
            }
        }
            return list;
    }

 }
