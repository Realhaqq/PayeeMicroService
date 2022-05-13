package com.haqq.payee.pojos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductRequest {
    private String productName;
    private String productType;
    private String productCode;
    private String uuid;
    private BigDecimal price;

}
