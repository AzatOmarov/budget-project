package de.budget.project.exceptions;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String e) {
        super(e);
    }
}
