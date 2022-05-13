package com.haqq.payee.pojos;

import com.haqq.payee.utils.DateAudit;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User extends DateAudit implements Serializable {

    private String username;

    private String fullName;

    private String email;
    private String uuid;

    private Boolean enable = true;

    private String walletId;
    private Date lastLogin;
}