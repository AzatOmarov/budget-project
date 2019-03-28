package de.budget.project.controller;

import de.budget.project.model.user.User;
import de.budget.project.model.wallet.Wallet;
import de.budget.project.model.wallet.WalletWebRequest;
import de.budget.project.model.wallet.WalletWebResponse;
import de.budget.project.services.UserService;
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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class WalletController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    WalletService walletService;

    @Autowired
    UserService userService;

    @PostMapping("/wallets")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Long createWallet(@RequestBody WalletWebRequest walletWebRequest) {
        Wallet wallet = convertToEntity(walletWebRequest);
        walletService.createWallet(wallet);
        return null;

    }

    @GetMapping("/wallets/{id}")
    public WalletWebResponse getWalletById(@PathVariable("id") Long id) {
        return walletService.getWalletById(id);

    }

    @GetMapping("/wallets/user/{id}")
    public List<WalletWebResponse> getWalletByUserId(@PathVariable("id") Long userId) {
        List<Wallet> wallets = walletService.getAllByUserId(userId);
        return convertToListWebResponse(wallets);
    }

    private Wallet convertToEntity(WalletWebRequest walletWebRequest) {
        Wallet wallet = modelMapper.map(walletWebRequest, Wallet.class);
        return wallet;
    }

    private WalletWebRequest convertToWebRequest(Wallet wallet) {
        WalletWebRequest walletWebRequest = new WalletWebRequest();
        walletWebRequest.setUserId(wallet.getUser().getId());
        walletWebRequest.setCurrency(wallet.getCurrency());
        return walletWebRequest;
    }

    private WalletWebResponse convertToWebResponse(Wallet wallet) {
        return modelMapper.map(wallet, WalletWebResponse.class);
    }

    private List<WalletWebResponse> convertToListWebResponse(List<Wallet> wallets) {
        return wallets
                .stream()
                .map(this::convertToWebResponse)
                .collect(Collectors.toList());
    }
}