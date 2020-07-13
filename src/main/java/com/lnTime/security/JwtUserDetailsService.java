package com.lnTime.security;

import com.lnTime.domain.UserEntity;
import com.lnTime.security.jwt.JwtUser;
import com.lnTime.security.jwt.JwtUserFactory;
import com.lnTime.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Primary
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String mail) {

        UserEntity user = userService.findByMail(mail);

        if (user == null) {
            throw new UsernameNotFoundException("User with email: " + mail + " not found.");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);

        return jwtUser;
    }
}