package com.haqq.payee.pojos;


import com.haqq.payee.utils.DateAudit;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SettlementWallet {
    private BigDecimal balance;
    private String walletId;

}