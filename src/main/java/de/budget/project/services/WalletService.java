package de.budget.project.services;

import de.budget.project.model.wallet.Wallet;
import de.budget.project.model.wallet.WalletWebResponse;

import java.util.List;

public interface WalletService {


    Long createWallet(Wallet wallet);

    WalletWebResponse getWalletById(Long id);

    List<Wallet> getAllByUserId(Long userId);
}