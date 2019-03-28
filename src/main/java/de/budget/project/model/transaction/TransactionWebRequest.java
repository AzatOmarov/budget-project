package de.budget.project.model.transaction;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionWebRequest {
    private Date customDate;
    private String currency;
    private String amount;
    private Long walletId;
    private Long categoryId;
    private String description;
}