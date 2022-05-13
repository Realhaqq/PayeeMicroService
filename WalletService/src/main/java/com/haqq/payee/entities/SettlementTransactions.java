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
@Table(name = "settlement_transactions", uniqueConstraints = {})

public class SettlementTransactions extends DateAudit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    private String contentCreatorId;

    private String contentId;

    private String contentType;

    @Column(length=20)
    private String walletId;



    public SettlementTransactions(BigDecimal amount, String contentCreatorId, String contentId, String contentType, String walletId) {
        this.amount = amount;
        this.contentCreatorId = contentCreatorId;
        this.contentId = contentId;
        this.contentType = contentType;
        this.walletId = walletId;

    }


    @Override
    public boolean equals(Object user) {
        return this.id.equals(((SettlementTransactions)user).getId());

    }

}