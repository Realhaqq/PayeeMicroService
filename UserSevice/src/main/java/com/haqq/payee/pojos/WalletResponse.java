package com.haqq.payee.pojos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WalletResponse {
    private BigDecimal balance;
    private String walletId;
    private String userId;
}
