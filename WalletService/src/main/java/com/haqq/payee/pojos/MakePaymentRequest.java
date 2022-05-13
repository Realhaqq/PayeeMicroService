package com.haqq.payee.pojos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MakePaymentRequest {
    private String contentCreatorId;
    private String contentId;
    private String contentType;
    private BigDecimal amount;
}
