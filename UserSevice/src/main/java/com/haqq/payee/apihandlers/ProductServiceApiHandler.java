package com.haqq.payee.apihandlers;

import com.google.gson.GsonBuilder;
import com.haqq.payee.pojos.*;
import com.haqq.payee.retrofitservice.ProductService;
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
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ProductServiceApiHandler {

    @Value("${product.base.url}")
    private String baseURL;

    @Value("${product.token}")
    private String token;

    Logger logger = LoggerFactory.getLogger(ProductServiceApiHandler.class);
    private ProductService productService;

    @PostConstruct
    public void init() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                .baseUrl(baseURL)
                .build();

        productService = retrofit.create(ProductService.class);

    }

    public Response<String> addProduct(CreateProductRequest request) throws Exception {

        String token = "Bearer " + this.token;

        return productService.addProduct(token, request).execute();
    }


    public Response<String> updateProduct(CreateProductRequest request) throws Exception {

        String token = "Bearer " + this.token;

        return productService.updateProduct(token, request).execute();
    }


    public Response<Product> deleteProduct(String productId) throws Exception {

        String token = "Bearer " + this.token;

        return productService.deleteProduct(token, productId).execute();
    }

    public Response<List<Product>> getAllProducts() throws Exception {

        String token = "Bearer " + this.token;

        return productService.getAllProducts(token).execute();
    }


    public Response<List<Product>> getProductsByCreator(String creatorId) throws Exception {

        String token = "Bearer " + this.token;

        return productService.getAllProductsByCreator(token, creatorId).execute();
    }

}
