package com.lnTime.api.user;

import com.lnTime.domain.UserEntity;
import com.lnTime.dto.user.LoginDTO;
import com.lnTime.dto.user.UserRegistrationDTO;
import com.lnTime.security.jwt.JwtTokenProvider;
import com.lnTime.service.UserService;
import com.lnTime.service.util.exception.InactiveUserException;
import com.lnTime.service.util.exception.UserNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public AuthController(final AuthenticationManager authenticationManager, final JwtTokenProvider jwtTokenProvider, final UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public String login(@RequestBody LoginDTO loginDto){
        try {

            String mail = loginDto.getMail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mail, loginDto.getPassword()));

            UserEntity user = userService.findByMail(mail);
            if(user == null){
                throw new UserNotFoundException(mail);
            }
            if (user.getIsDeleted()) {
                throw new InactiveUserException(mail);
            }

            return jwtTokenProvider.createToke(mail, new ArrayList<>(user.getRoles()));

        }catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid email or password");
        }

    }

    @PostMapping("register")
    public void register(@RequestBody UserRegistrationDTO user){

        userService.register(user);
    }

}