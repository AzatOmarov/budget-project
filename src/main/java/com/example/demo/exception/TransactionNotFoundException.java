package com.example.demo.exception;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String e) {
        super(e);
    }
}
