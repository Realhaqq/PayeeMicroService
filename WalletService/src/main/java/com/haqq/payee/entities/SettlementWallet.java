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
@Table(name = "settlement_wallet", uniqueConstraints = {})

public class SettlementWallet extends DateAudit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal balance;

    private String role;

    @Column(length=20)
    private String walletId;


    public SettlementWallet(SettlementWallet wallet) {
        this.balance = wallet.balance;
        this.walletId = wallet.walletId;
        this.role = wallet.role;
    }

    public SettlementWallet() {
    }


    @Override
    public boolean equals(Object user) {
        return this.id.equals(((SettlementWallet)user).getId());

    }

}