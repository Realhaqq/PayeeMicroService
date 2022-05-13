package com.haqq.payee.controllers;

import com.haqq.payee.pojos.CreateProductRequest;
import com.haqq.payee.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@Slf4j
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/products/add")
    public ResponseEntity<?> createProduct(@Valid @RequestBody CreateProductRequest createProductRequest) {
        return productService.createProduct(createProductRequest);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/creator/{creatorUuid}")
    public ResponseEntity<?> getProductsByCreatorUuid(@PathVariable String creatorUuid) {
        return productService.getProductsByCreatorUuid(creatorUuid);
    }

    @DeleteMapping("/products/{productCode}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productCode) {
        return productService.deleteProduct(productCode);
    }

    @PutMapping("/products/{productCode}")
    public ResponseEntity<?> updateProduct(@PathVariable String productCode, @Valid @RequestBody CreateProductRequest createProductRequest) {
        return productService.updateProduct(productCode, createProductRequest);
    }
}
