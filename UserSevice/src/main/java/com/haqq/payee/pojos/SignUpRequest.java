package com.haqq.payee.pojos;

import com.haqq.payee.enums.RoleName;
import lombok.Data;

import javax.persistence.Enumerated;

@Data
public class SignUpRequest {
    private String fullName;
    private String username;
    private String email;
    private String password;
    @Enumerated
    private RoleName role;
}
