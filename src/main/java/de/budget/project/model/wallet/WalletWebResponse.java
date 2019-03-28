package de.budget.project.model.wallet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletWebResponse {
    private Long walletId;
    private String currency;

    public WalletWebResponse(String currency) {
        this.currency = currency;
    }
}