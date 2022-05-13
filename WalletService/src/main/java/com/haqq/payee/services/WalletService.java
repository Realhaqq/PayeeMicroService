package com.haqq.payee.services;

import com.haqq.payee.controllers.WalletController;
import com.haqq.payee.entities.SettlementWallet;
import com.haqq.payee.entities.Wallet;
import com.haqq.payee.enums.RoleName;
import com.haqq.payee.pojos.ApiResponse;
import com.haqq.payee.pojos.CreateWalletRequest;
import com.haqq.payee.pojos.MakePaymentRequest;
import com.haqq.payee.pojos.MakePaymentResponse;
import com.haqq.payee.repositories.SettlementWalletRepository;
import com.haqq.payee.repositories.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private SettlementWalletRepository settlementWalletRepository;

    Logger logger = LoggerFactory.getLogger(WalletController.class);

    public String createWallet(CreateWalletRequest request) {
        Wallet wallet = new Wallet();
        wallet.setWalletId(request.getWalletId());
        wallet.setUserId(request.getUuid());

        walletRepository.save(wallet);

        return wallet.getWalletId();
    }

    public Wallet getWallet(String uuid) {
        Wallet wallet = walletRepository.findByUserId(uuid);

        if (wallet == null) {
            return null;
        }else {
            return wallet;
        }
    }

    public MakePaymentResponse makePayment(MakePaymentRequest request) {
        BigDecimal totalParcentage = (request.getAmount().multiply(new BigDecimal(5)).divide(new BigDecimal(100)));
        logger.info("Total percentage: " + totalParcentage);
        SettlementWallet settlementWalletClient = settlementWalletRepository.findByRole(String.valueOf(RoleName.ROLE_CLIENT_INSTITUTION));
        BigDecimal amountToClient = totalParcentage;
        settlementWalletClient.setBalance(settlementWalletClient.getBalance().add(amountToClient));
        settlementWalletRepository.save(settlementWalletClient);


        SettlementWallet settlementWalletContractor = settlementWalletRepository.findByRole(String.valueOf(RoleName.ROLE_CONTRACTOR_INSTITUTION));
        BigDecimal amountToContractor = totalParcentage;
        settlementWalletContractor.setBalance(settlementWalletContractor.getBalance().add(amountToContractor));
        settlementWalletRepository.save(settlementWalletContractor);


        // get contractor wallet
        Wallet contractorWallet = walletRepository.findByUserId(request.getContentCreatorId());
        contractorWallet.setBalance(contractorWallet.getBalance().add(request.getAmount().subtract(totalParcentage).subtract(totalParcentage)));
        walletRepository.save(contractorWallet);


        MakePaymentResponse response = new MakePaymentResponse();
        response.setMessage("Payment made successfully");
        response.setStatus("success");
        response.setTransactionAmount(request.getAmount());
        response.setTransactionId(System.currentTimeMillis() + "");

        return response;

    }
}
