package com.example.demosecurityjwt.security;

import com.example.demosecurityjwt.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class CustomerUserDetail implements UserDetails {
    private Long id;
    private String name;
    private String username;
    private String password;
    private Collection<GrantedAuthority> authorities = new HashSet<>();

    public static CustomerUserDetail mapToUserDetail(User user){
        List<GrantedAuthority>  authorities = user.getRoleSet()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return new CustomerUserDetail(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getPassword(),
                authorities
        );

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
}
