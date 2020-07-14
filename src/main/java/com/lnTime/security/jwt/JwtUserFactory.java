package com.lnTime.security.jwt;

import com.lnTime.domain.UserEntity;
import com.lnTime.domain.util.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public static JwtUser create(UserEntity user) {

        return new JwtUser(user.getId(), user.getMail(), user.getFirstName(), user.getLastName(),
                user.getPassword(), user.getIsDeleted(), user.getIsActivated(), mapToGrantedAuthorities(user.getRoles()));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Set<UserRole> userRoles) {
        return userRoles.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.name()))
                .collect(Collectors.toList());
    }
}