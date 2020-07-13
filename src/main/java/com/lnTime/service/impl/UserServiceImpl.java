package com.lnTime.service.impl;

import com.lnTime.domain.UserEntity;
import com.lnTime.domain.util.UserRole;
import com.lnTime.dto.user.UserInfoDTO;
import com.lnTime.dto.user.UserRegistrationDTO;
import com.lnTime.repository.UserRepository;
import com.lnTime.security.jwt.JwtUser;
import com.lnTime.service.UserService;
import com.lnTime.service.util.exception.UserAlreadyExistsException;
import com.lnTime.service.util.exception.UserNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(final UserRepository userRepository, final BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserEntity findByMail(String mail) {
        Optional<UserEntity> byMail = userRepository.findByMail(mail);

        UserEntity user = null;

        if(byMail.isPresent()){
            user = byMail.get();
        }

        return user;
    }

    @Override
    public UserInfoDTO findById(Long id) {
        Optional<UserEntity> byId = userRepository.findById(id);

        UserEntity user = null;

        if(byId.isPresent()){
            user = byId.get();
        }

        return user == null ? null : UserInfoDTO.mapFromEntity(user);
    }

    @Override
    @Transactional
    public void remove(Long id) {

        Optional<UserEntity> byId = userRepository.findById(id);
        if(byId.isPresent()){
            UserEntity userEntity = byId.get();
            userEntity.setIsDeleted(true);
            userRepository.save(userEntity);
        }

        else {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public void update(UserInfoDTO user) {

        Optional<UserEntity> byId = userRepository.findById(user.getId());
        if(byId.isPresent()){
            UserEntity userEntity = byId.get();
            user.toEntity(userEntity);
            userRepository.save(userEntity);
        }

        else {
            throw new UserNotFoundException(user.getId());
        }
    }

    @Override
    @Transactional
    public void register(UserRegistrationDTO user) {

        Optional<UserEntity> byMail = userRepository.findByMail(user.getMail());
        if(byMail.isPresent()){
            throw new UserAlreadyExistsException(user.getMail());
        }

        UserEntity userEntity = user.toEntity();
        userEntity.getRoles().add(UserRole.ROLE_TEACHER);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userEntity);

    }

    @Override
    public Long getMe() {

        return ((JwtUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getId();
    }
}