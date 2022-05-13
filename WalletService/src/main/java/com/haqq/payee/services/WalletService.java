package com.haqq.payee.services;

import com.haqq.payee.entities.Wallet;
import com.haqq.payee.pojos.CreateWalletRequest;
import com.haqq.payee.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    public String createWallet(CreateWalletRequest request) {
        Wallet wallet = new Wallet();
        wallet.setWalletId(request.getWalletId());
        wallet.setUserId(request.getUuid());

        walletRepository.save(wallet);

        return wallet.getWalletId();
    }
}
