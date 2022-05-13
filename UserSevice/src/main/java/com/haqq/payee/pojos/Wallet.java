package com.haqq.payee.pojos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Wallet {
    BigDecimal balance;
    String walletId;
    String userId;
}
