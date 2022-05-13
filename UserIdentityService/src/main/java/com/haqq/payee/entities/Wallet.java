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
@Table(name = "wallet", uniqueConstraints = {})

public class Wallet extends DateAudit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal balance;

    private String userId;

    @Column(length=20)
    private String walletId;

    public Wallet(Wallet wallet) {
        this.balance = wallet.balance;
        this.walletId = wallet.walletId;
        this.userId = wallet.userId;
    }

    public Wallet() {
    }


    @Override
    public boolean equals(Object user) {
        return this.id.equals(((Wallet)user).getId());

    }

}