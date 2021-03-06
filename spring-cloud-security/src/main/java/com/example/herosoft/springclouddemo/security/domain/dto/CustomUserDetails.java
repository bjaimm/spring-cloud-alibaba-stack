package com.example.herosoft.springclouddemo.security.domain.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;


public class CustomUserDetails implements UserDetails {

    private List<GrantedAuthority> authorities;
    private String username;
    private String password;
    private String deptno;//这个是customized的信息
    private boolean isAccountNonExpired ;
    private boolean isAccountNonLocked ;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    public CustomUserDetails(String username, String password, String deptno,boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities){

        if (((username == null) || "".equals(username)) || (password == null)) {
            throw new IllegalArgumentException(
                    "Cannot pass null or empty values to constructor");
        }

        this.username = username;
        this.password = password;
        this.deptno=deptno;
        this.isEnabled = enabled;
        this.isAccountNonExpired = accountNonExpired;
        this.isCredentialsNonExpired = credentialsNonExpired;
        this.isAccountNonLocked = accountNonLocked;
        this.authorities = new ArrayList<>(authorities);
    }
    public CustomUserDetails(String username, String password,String deptno,Collection<? extends GrantedAuthority> authorities) {
        this(username, password,deptno, true, true, true, true, authorities);
    }
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
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
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = new ArrayList<>(authorities) ;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getDeptno() {
        return deptno;
    }
}
