package com.silicon.test.testtask.Model;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {
    private String role = "ROLE_USER";

    public Role() {}

    public Role(String role) {
        this.role = role;
    }
    @Override
    public String getAuthority() {
        return role;
    }
}
