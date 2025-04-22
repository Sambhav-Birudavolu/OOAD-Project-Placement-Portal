package com.pesuplacements.util;

import com.pesuplacements.entity.StudentCred;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomStudentDetails implements UserDetails {

    private final StudentCred studentCred;

    public CustomStudentDetails(StudentCred studentCred) {
        this.studentCred = studentCred;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
        return authorities;
    }
    


    @Override
    public String getPassword() {
        return studentCred.getPassword();
    }

    @Override
    public String getUsername() {
        return studentCred.getSrn();
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

    public StudentCred getStudentCred() {
        return studentCred;
    }
}
