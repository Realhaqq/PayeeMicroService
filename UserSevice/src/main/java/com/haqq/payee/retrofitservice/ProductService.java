package com.haqq.payee.retrofitservice;

import com.haqq.payee.pojos.CreateProductRequest;
import com.haqq.payee.pojos.Product;
import retrofit2.http.*;
import retrofit2.Call;

import java.util.List;


public interface ProductService {
    String ADD_PRODUCT = "api/v1/products/add";
    String UPDATE_PRODUCT = "api/v1/products/{productCode}";
    String GET_ALL_PRODUCTS = "api/v1/products";
    String GET_PRODUCT_BY_CREATOR = "api/v1/products/creator/{creatorUuid}";
    String DELETE_PRODUCT = "api/v1/products/{productCode}";

    @POST(ADD_PRODUCT)
    Call<String> addProduct(@Header("Authorization") String token, @Body CreateProductRequest createProductRequest);

    @POST(UPDATE_PRODUCT)
    Call<String> updateProduct(@Header("Authorization") String token, @Body CreateProductRequest createProductRequest);


    @GET(GET_ALL_PRODUCTS)
    Call<List<Product>> getAllProducts(@Header("Authorization") String token);

    @GET(GET_PRODUCT_BY_CREATOR)
    Call<List<Product>> getAllProductsByCreator(@Header("Authorization") String token, @Path("creatorUuid") String creatorUuid);

    @DELETE(DELETE_PRODUCT)
    Call<Product> deleteProduct(@Header("Authorization") String token, @Path("productCode") String productCode);


}
