package com.haqq.payee.retrofitservice;

import com.haqq.payee.pojos.CreateWalletRequest;
import com.haqq.payee.pojos.MakePaymentRequest;
import com.haqq.payee.pojos.MakePaymentResponse;
import com.haqq.payee.pojos.Wallet;
import retrofit2.Call;
import retrofit2.http.*;

public interface WalletService {
    String CREATE_WALLET = "api/v1/user/createWallet";
    String USER_WALLET = "api/v1/user/getWallet/{uuid}";
    String MAKE_PAYMENT = "api/v1/user/makePayment";

    @POST(CREATE_WALLET)
    Call<String> createUserWallet(@Header("Authorization") String token, @Body CreateWalletRequest createWalletRequest);


    @GET(USER_WALLET)
    Call<Wallet> getUserWallet(@Header("Authorization") String token, @Path("uuid") String userId);


    @POST(MAKE_PAYMENT)
    Call<MakePaymentResponse> makePayment(@Header("Authorization") String token, @Body MakePaymentRequest makePaymentRequest);

}
