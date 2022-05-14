package com.haqq.payee.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.haqq.payee.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserPrincipal implements UserDetails {
    private Long id;

    private String fullName;

    private String username;


    private String phoneNumber;
    private String uuid;
    private String walletId;
    private Boolean enable;


    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(User userPrincipal, Collection<? extends GrantedAuthority> authorities) {
        this.id = userPrincipal.getId();
        this.fullName = userPrincipal.getFullName();
        this.username = userPrincipal.getUsername();
        this.email = userPrincipal.getEmail();
        this.password = userPrincipal.getPassword();
        this.uuid = userPrincipal.getUuid();
        this.enable = userPrincipal.getEnable();
        this.walletId = userPrincipal.getWalletId();
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UserPrincipal(user,
                authorities
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

}
