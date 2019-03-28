package de.budget.project.controller;

import de.budget.project.model.CustomMoney;
import de.budget.project.model.category.Category;
import de.budget.project.model.transaction.Transaction;
import de.budget.project.model.transaction.TransactionWebRequest;
import de.budget.project.model.transaction.TransactionWebResponse;
import de.budget.project.model.wallet.Wallet;
import de.budget.project.repository.TransactionRepository;
import de.budget.project.services.CategoryService;
import de.budget.project.services.TransactionService;
import de.budget.project.services.WalletService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TransactionService transactionService;
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    WalletService walletService;

    @Autowired
    CategoryService categoryService;

    @PostMapping("/transactions")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@RequestBody TransactionWebRequest transactionWebRequest) {
                transactionService.insertTransaction(transactionWebRequest.getCustomDate(),
                transactionWebRequest.getAmount(),
                transactionWebRequest.getCurrency(),
                transactionWebRequest.getWalletId(),
                transactionWebRequest.getCategoryId(),
                transactionWebRequest.getDescription());
    }

    @GetMapping("/transactions/{id}")
    public TransactionWebResponse getTransactionById(@PathVariable("id") Long id) {
        return convertToWebResponse(transactionService.getTransactionById(id));
    }

    @GetMapping("/transactions/wallet/{id}")
    public List<TransactionWebResponse> getTransactionsByWalletId(@PathVariable("id") Long walletId) {
        List<Transaction> transactions = transactionService.getTransactionsByWalletId(walletId);
        return convertToListWebResponse(transactions);
    }

    private TransactionWebResponse convertToWebResponse(Transaction transaction) {
        TransactionWebResponse transactionWebResponse = new TransactionWebResponse();
        transactionWebResponse.setAmount(transaction.getAmount().toString());
        transactionWebResponse.setCategoryName(transaction.getCategory().getName());
        transactionWebResponse.setDescription(transaction.getDescription());
        transactionWebResponse.setBalance(transactionService.recalculateBalance(transaction.getWallet().getId()));
        return transactionWebResponse;
    }

    private List<TransactionWebResponse> convertToListWebResponse(List<Transaction> transactions) {
        return transactions
                .stream()
                .map(this::convertToWebResponse)
                .collect(Collectors.toList());
    }
//    @GetMapping("/transactions/balance/{id}")
//    public BigDecimal getBalance(@PathVariable("id") Long id){
//        return transactionRepository.calculateBalance(id);

//    }
//    private Transaction convertToEntity(TransactionWebRequest transactionWebRequest) {
//        Wallet wallet = walletService.getWalletById(transactionWebRequest.getWalletId());
//        Category category = categoryService.getCategoryById(transactionWebRequest.getCategoryId());
//        Transaction transaction = new Transaction();
//        transaction.setCustomDate(transactionWebRequest.getCustomDate());
//        transaction.setAmount(transactionWebRequest.getAmount());
//        transaction.setWallet(wallet);
//        transaction.setCategory(category);
//        transaction.setDescription(transactionWebRequest.getDescription());
//        return transaction;

//    }
//    private TransactionWebRequest convertToWebRequest(Transaction transaction) {

//        return modelMapper.map(transaction, TransactionWebRequest.class);
//    }
}