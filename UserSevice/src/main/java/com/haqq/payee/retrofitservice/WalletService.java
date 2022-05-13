package com.haqq.payee.retrofitservice;

import com.haqq.payee.pojos.*;
import retrofit2.Call;
import retrofit2.http.*;

public interface WalletService {
    String CREATE_WALLET = "api/v1/user/createWallet";
    String USER_WALLET = "api/v1/user/getWallet/{uuid}";
    String MAKE_PAYMENT = "api/v1/user/makePayment";
    String GET_SETTLEMENT_WALLET = "api/v1/institution/getSettlementWallet/{role}";

    @POST(CREATE_WALLET)
    Call<String> createUserWallet(@Header("Authorization") String token, @Body CreateWalletRequest createWalletRequest);


    @GET(USER_WALLET)
    Call<Wallet> getUserWallet(@Header("Authorization") String token, @Path("uuid") String userId);


    @POST(MAKE_PAYMENT)
    Call<MakePaymentResponse> makePayment(@Header("Authorization") String token, @Body MakePaymentRequest makePaymentRequest);


    @GET(GET_SETTLEMENT_WALLET)
    Call<SettlementWallet> getInstitutionWallet(@Header("Authorization") String token, @Path("role") String role);

}
