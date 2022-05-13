package com.haqq.payee.pojos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MakePaymentResponse {
    private String status;
    private String message;
    private String transactionId;
    private String transactionStatus;
    private BigDecimal transactionAmount;
}
