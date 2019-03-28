package de.budget.project.services.impl;

import de.budget.project.model.wallet.Wallet;
import de.budget.project.model.wallet.WalletWebResponse;
import de.budget.project.repository.WalletRepository;
import de.budget.project.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Override
    public Long createWallet(Wallet wallet) {
        walletRepository.createWallet(wallet.getUser().getId(), wallet.getCurrency());
        List<Wallet> allByUserId = walletRepository.getAllByUserId(wallet.getUser().getId());
        for (Wallet w: allByUserId) {

        }
        return null;

    }

    @Override
    public WalletWebResponse getWalletById(Long id) {
        return walletRepository.getWalletById(id);
    }

    @Override
    public List<Wallet> getAllByUserId(Long userId) {
        return walletRepository.getAllByUserId(userId);
    }
}