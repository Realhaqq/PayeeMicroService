package com.haqq.payee.controllers;

import com.haqq.payee.pojos.CreateProductRequest;
import com.haqq.payee.security.CurrentUser;
import com.haqq.payee.security.UserPrincipal;
import com.haqq.payee.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @PreAuthorize("hasRole('ROLE_CONTENT_CREATOR')")
    @PostMapping("/products/add")
    public ResponseEntity<?> createProduct(@Valid @RequestBody CreateProductRequest createProductRequest, @CurrentUser UserPrincipal currentUser) {
        return productService.createProduct(createProductRequest, currentUser);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/creator/{creatorUuid}")
    public ResponseEntity<?> getProductsByCreatorUuid(@PathVariable String creatorUuid) {
        return productService.getProductsByCreatorUuid(creatorUuid);
    }

    @PreAuthorize("hasRole('ROLE_CONTENT_CREATOR')")
    @DeleteMapping("/products/{productCode}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productCode) {
        return productService.deleteProduct(productCode);
    }

    @PreAuthorize("hasRole('ROLE_CONTENT_CREATOR')")
    @PutMapping("/products/{productCode}")
    public ResponseEntity<?> updateProduct(@PathVariable String productCode, @Valid @RequestBody CreateProductRequest createProductRequest) {
        return productService.updateProduct(productCode, createProductRequest);
    }
}
