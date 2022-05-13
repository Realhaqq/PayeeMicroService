package com.haqq.payee.controllers;


import com.haqq.payee.pojos.CreateWalletRequest;
import com.haqq.payee.services.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@Slf4j
@Validated
public class WalletController {

    @Autowired
    private WalletService walletService;
    Logger logger = LoggerFactory.getLogger(WalletController.class);

    @PostMapping("/user/createWallet")
    public String createWallet(@RequestBody CreateWalletRequest request) {
        logger.info("createWallet called");
        return walletService.createWallet(request);

    }

}
