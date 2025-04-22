package com.pesuplacements.util;

import com.pesuplacements.entity.AdminCred;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomAdminDetails implements UserDetails {

    private final AdminCred adminCred;  // Replace Users with StudentCred

    public CustomAdminDetails(AdminCred adminCred) {
        this.adminCred = adminCred;  // Store the StudentCred object
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // 🔥 This is the fix
        return authorities;
    }
    


    @Override
    public String getPassword() {
        return adminCred.getPassword();  // Return password from StudentCred entity
    }

    @Override
    public String getUsername() {
        return adminCred.getAid();
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

    public AdminCred getAdminCred() {
        return adminCred;
    }
}
