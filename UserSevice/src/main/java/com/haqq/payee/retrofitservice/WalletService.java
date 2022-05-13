package com.haqq.payee.retrofitservice;

import com.haqq.payee.pojos.CreateWalletRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface WalletService {
    String CREATE_WALLET = "api/v1/user/createWallet";

    @POST(CREATE_WALLET)
    Call<String> createUserWallet(@Header("Authorization") String token, @Body CreateWalletRequest createWalletRequest);

}
