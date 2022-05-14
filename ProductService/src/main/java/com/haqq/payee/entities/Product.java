package com.haqq.payee.entities;


import com.haqq.payee.utils.DateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "products", uniqueConstraints = {})

public class Product extends DateAudit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal price;

    private String productCode;
    private String productName;
    private String productType;


    private String creatorUuid;

    public Product(Product product) {
        this.price = product.price;
        this.productCode = product.productCode;
        this.productType = product.productType;
    }

    public Product() {
    }


    @Override
    public boolean equals(Object user) {
        return this.id.equals(((Product)user).getId());

    }

}