package de.budget.project.model.wallet;

import lombok.Data;

@Data
public class WalletWebRequest {
    private Long userId;
    private String currency;
}