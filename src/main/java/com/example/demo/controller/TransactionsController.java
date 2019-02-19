package com.example.demo.controller;

import com.example.demo.model.Transaction;
import com.example.demo.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class TransactionsController {


    @Autowired
    public TransactionsService transactionsService;

    @GetMapping("/transactions")
    public List<Transaction> transactions(){
        return transactionsService.getTransactionsList();
    }

    @PostMapping("/transactions")
    public void addTransaction(@RequestBody Transaction transaction){
        transactionsService.addTransaction(transaction);
    }

    @GetMapping("/transactions/{id}")
    public Transaction getTransactionById(@PathVariable int id){
        return transactionsService.getTransactionById(id);
    }

    @GetMapping("transactions/sortByCategory/{category}")
    public List<Transaction> getTransactionsSortedByCategory(@PathVariable("category") String category){
        return transactionsService.getTransactionsByCategory(category);
    }

    @GetMapping("transactions/sortByAmount/{amount}")
    public List<Transaction> getTransactionsByAmount(@PathVariable("amount") int amount){
        return transactionsService.getTransactionsBiggerThan(amount);
    }


}
