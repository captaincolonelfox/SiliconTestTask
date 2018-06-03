package com.silicon.test.testtask.Model;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String role;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;


    public User() {}

    public User(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.role = roles;
    }


    @Override
    public Collection<Role> getAuthorities() {
        return new HashSet<>(Arrays.asList(new Role(role)));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "[username:" + username + ";" +
                "password:" + password + ";" +
                "role:" + role + ";]";
    }

    public void setRoles(String role) {
        this.role = role;
    }
}
