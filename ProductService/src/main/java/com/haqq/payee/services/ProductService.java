package com.haqq.payee.services;

import com.haqq.payee.entities.Product;
import com.haqq.payee.pojos.ApiResponse;
import com.haqq.payee.pojos.CreateProductRequest;
import com.haqq.payee.repositories.ProductRepository;
import com.haqq.payee.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    Logger logger = LoggerFactory.getLogger(ProductService.class);

    public ResponseEntity<?> createProduct(CreateProductRequest createProductRequest, UserPrincipal currentUser) {
        Product product = new Product();
        product.setProductName(createProductRequest.getProductName());
        product.setProductType(createProductRequest.getProductType());
        product.setProductCode(System.currentTimeMillis() + "");
        product.setCreatorUuid(currentUser.getUuid());
        product.setPrice(createProductRequest.getPrice());

        String uuid = UUID.randomUUID().toString();

        productRepository.save(product);

        return new ResponseEntity(new ApiResponse(true, "Product Added Successfully", 000, product),
                HttpStatus.OK);


    }

    public ResponseEntity<?> getAllProducts() {
        return new ResponseEntity(new ApiResponse(true, "Products Fetched Successfully", 000, productRepository.findAll()),
                HttpStatus.OK);

    }

    public ResponseEntity<?> getProductsByCreatorUuid(String creatorUuid) {
        return new ResponseEntity(new ApiResponse(true, "Products Fetched Successfully", 000, productRepository.findByCreatorUuid(creatorUuid)),
                HttpStatus.OK);

    }

    public ResponseEntity<?> deleteProduct(String productCode) {
        productRepository.delete(productRepository.findByProductCode(productCode));
        return new ResponseEntity(new ApiResponse(true, "Product Deleted Successfully", 000, null),
                HttpStatus.OK);

    }

    public ResponseEntity<?> updateProduct(String productCode, CreateProductRequest createProductRequest) {
        Product product = productRepository.findByProductCode(productCode);
        if (product != null) {
            product.setProductName(createProductRequest.getProductName());
            product.setProductType(createProductRequest.getProductType());
            product.setPrice(createProductRequest.getPrice());

            productRepository.save(product);


            return new ResponseEntity(new ApiResponse(true, "Product Updated Successfully", 000, product),
                    HttpStatus.OK);

        } else {
            return new ResponseEntity(new ApiResponse(false, "Product Not Found", 000, null), HttpStatus.NOT_FOUND);
        }

    }
}
