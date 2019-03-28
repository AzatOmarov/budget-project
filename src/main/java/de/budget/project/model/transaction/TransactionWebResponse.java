package de.budget.project.model.transaction;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionWebResponse {
    private String amount;
    private String description;
    private String categoryName;
    private BigDecimal balance;
}