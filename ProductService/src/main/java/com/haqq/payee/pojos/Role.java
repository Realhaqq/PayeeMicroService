package com.haqq.payee.pojos;

import com.haqq.payee.enums.RoleName;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Data
public class Role {
    private RoleName name;

}
