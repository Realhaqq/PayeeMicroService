package com.haqq.payee.pojos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SettlementTransactions {
    private BigDecimal amount;

    private String contentCreatorId;

    private String contentId;

    private String contentType;

    private String walletId;
}
