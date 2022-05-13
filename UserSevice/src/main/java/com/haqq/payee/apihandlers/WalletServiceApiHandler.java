package com.haqq.payee.apihandlers;

import com.google.gson.GsonBuilder;
import com.haqq.payee.pojos.CreateWalletRequest;
import com.haqq.payee.pojos.MakePaymentRequest;
import com.haqq.payee.pojos.Wallet;
import com.haqq.payee.retrofitservice.WalletService;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class WalletServiceApiHandler {

    @Value("${wallet.base.url}")
    private String baseURL;

    @Value("${wallet.token}")
    private String token;

    Logger logger = LoggerFactory.getLogger(WalletServiceApiHandler.class);
    private WalletService walletService;

    @PostConstruct
    public void init() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                .baseUrl(baseURL)
                .build();

        walletService = retrofit.create(WalletService.class);

    }

    public Response<String> createWallet(CreateWalletRequest request) throws Exception {

        String token = "Bearer " + this.token;

        return walletService.createUserWallet(token, request).execute();
    }


    public Response<Wallet> getWallet(String userId) throws Exception {

        String token = "Bearer " + this.token;

        return walletService.getUserWallet(token, userId).execute();
    }

    public Response makePayment(MakePaymentRequest request) throws IOException {
        String token = "Bearer " + this.token;
        return walletService.makePayment(token, request).execute();
    }

    public Response getInstitutionWallet(String role) throws IOException {
        String token = "Bearer " + this.token;
        return walletService.getInstitutionWallet(token, role).execute();
    }
}
