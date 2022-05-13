package com.haqq.payee.pojos;

import lombok.Data;
import java.math.BigDecimal;

@Data

public class Product {
    private BigDecimal price;
    private String productCode;
    private String productName;
    private String productType;
    private String creatorUuid;
}