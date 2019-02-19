package com.example.demo.service;

import com.example.demo.model.Transaction;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedList;
import java.util.List;

@Service
public class TransactionsService {

    public List<Transaction> transactionsList = new LinkedList<>();

    public void addTransaction(Transaction transactions){
        transactionsList.add(transactions);
    }

    //    Get all transactions by Id
    public List<Transaction> getTransactionsList(){
        return transactionsList;
    }

//    Get transaction by Id
    public Transaction getTransactionById(int id){
        return transactionsList.get(id);
    }
    public void deleteTransactionById(int id){
        transactionsList.remove(id);
    }

    public List<Transaction> getTransactionsByCategory(String category){
        List<Transaction> listSortedByCategory = new LinkedList<>();
        for (Transaction transaction: transactionsList) {
            if(transaction.getCategory().equals(category)){
                listSortedByCategory.add(transaction);
            }
        }
        return listSortedByCategory;
    }

    public List<Transaction> getTransactionsBiggerThan(int minBoundary){
        List<Transaction> list = new LinkedList<>();
        for (Transaction transaction: transactionsList) {
            if (transaction.getAmount() >= minBoundary){
                list.add(transaction);
            }
        }
            return list;

    }


 }
