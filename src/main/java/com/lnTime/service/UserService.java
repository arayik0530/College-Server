package com.lnTime.service;

import com.lnTime.domain.UserEntity;
import com.lnTime.dto.user.UserInfoDTO;
import com.lnTime.dto.user.UserRegistrationDTO;

public interface UserService {

 UserEntity findByMail(String mail);

 UserInfoDTO findById(Long id);

 void remove(Long id);

 void update(UserInfoDTO user);

 void register(UserRegistrationDTO user);

 Long getMe();
}