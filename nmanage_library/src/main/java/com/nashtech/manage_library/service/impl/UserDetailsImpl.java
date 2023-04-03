package com.nashtech.manage_library.service.impl;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nashtech.manage_library.Entity.Account.UserAccount;

public class UserDetailsImpl implements UserDetails {

    static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private String role;

    public UserDetailsImpl(Long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static UserDetailsImpl build(UserAccount user) {

        return new UserDetailsImpl(
                user.getId(), 
                user.getUsername(), 
                user.getPassword(),
                user.getRole());

    }


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Long getId() {
        return id;
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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    
}
