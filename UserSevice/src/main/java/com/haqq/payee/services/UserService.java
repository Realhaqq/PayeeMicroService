package com.haqq.payee.services;

import com.haqq.payee.apihandlers.WalletServiceApiHandler;
import com.haqq.payee.pojos.*;
import com.haqq.payee.repositories.RoleRepository;
import com.haqq.payee.repositories.UserRepository;
import com.haqq.payee.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Response;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    WalletServiceApiHandler walletServiceApiHandler;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public Wallet getUserWallet(UserPrincipal currentUser) {
        try {
            Response response = walletServiceApiHandler.getWallet(currentUser.getUuid());

            if (response.isSuccessful()) {
                logger.info("Wallet found for user: " + response.body());
                return (Wallet) response.body();
            } else {
                return new Wallet();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error while fetching wallet for user: " +e.getMessage());
            return new Wallet();
        }
    }

    public ResponseEntity<?> makePayment(UserPrincipal currentUser, MakePaymentRequest request) {
        try {
            Response<MakePaymentResponse> response = walletServiceApiHandler.makePayment(request);

            if (response.isSuccessful()) {
                logger.info("Payment made successfully!!!!");
                return new ResponseEntity(new ApiResponse(false, "Payment Made Successfully", 000, response.body()),
                        HttpStatus.OK);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error while making payment: " +e.getMessage());
        }

        return new ResponseEntity(new ApiResponse(false, "Payment Failed", 101, null),
                HttpStatus.BAD_REQUEST);

    }

    public SettlementWallet getInstitutionWallet(String role) {
        logger.info("Fetching institution wallet for role: " +role);
        try {
            Response response = walletServiceApiHandler.getInstitutionWallet(role);

            if (response.isSuccessful()) {
                logger.info("Institution wallet found for user: " + response.body());
                return (SettlementWallet) response.body();
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error while fetching institution wallet for user: " +e.getMessage());
        }

        return new SettlementWallet();
    }
}
