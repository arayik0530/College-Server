package com.lnTime.service;

import com.lnTime.domain.UserEntity;
import com.lnTime.dto.user.PasswordChangingDTO;
import com.lnTime.dto.user.UserInfoDTO;
import com.lnTime.dto.user.UserRegistrationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserEntity findByMail(String mail);

    UserInfoDTO findById(Long id);

    void remove(Long id);

    void update(UserInfoDTO user);

    void register(UserRegistrationDTO user);

    Long getMe();

    Page<UserEntity> findAllUsers(Pageable pageable);

    void updatePassword(PasswordChangingDTO passwordChangingDto);

    void makeActive(Long id);

    void makeInactive(Long id);
}