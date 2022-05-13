package com.haqq.payee.entities;

import com.haqq.payee.utils.DateAudit;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})

public class User extends DateAudit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 15)
    private String username;

    @NotBlank
    @Size(max = 15)
    private String fullName;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;
    private String uuid;

    @NotBlank
    @Size(max = 100)
    private String password;


    private Boolean enable = true;


    @Column(length=70)
    private String walletId;


    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @Column(length=32, nullable = false)
    private String phoneNumber;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(User user) {
        this.fullName = user.fullName;
        this.email = user.email;
        this.password = user.password;
        this.uuid = user.uuid;
        this.walletId = user.walletId;
        this.lastLogin = user.lastLogin;
    }

    public User() {
    }


    @Override
    public boolean equals(Object user) {
        return this.id.equals(((User)user).getId());

    }

    @Override
    public String toString() {
        return this.fullName+" "+this.getFullName();
    }

}