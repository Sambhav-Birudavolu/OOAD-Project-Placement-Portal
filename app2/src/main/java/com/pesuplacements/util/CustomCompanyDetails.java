package com.pesuplacements.util;

import com.pesuplacements.entity.CompanyCred;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomCompanyDetails implements UserDetails {

    private final CompanyCred companyCred;

    public CustomCompanyDetails(CompanyCred companyCred) {
        this.companyCred = companyCred;  
    }

    @Override
    public String getPassword() {
        return companyCred.getPassword();  // Returning password from CompanyCred
    }

    @Override
    public String getUsername() {
        return companyCred.getCompanyName(); // This should work if CompanyCred has this method
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_COMPANY"));
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

    public CompanyCred getCompanyCred() {
        return companyCred; 
    }
}
