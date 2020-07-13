package com.lnTime.config;

import com.lnTime.domain.UserEntity;
import com.lnTime.domain.util.UserRole;
import com.lnTime.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DefaultAdminLoaderConfig implements ApplicationRunner {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public DefaultAdminLoaderConfig(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        String mail = "admin@admin.com";

        Optional<UserEntity> temp = userRepository.findByMail(mail);

        if(!temp.isPresent()){
            UserEntity admin = new UserEntity();

            admin.setMail(mail);
            admin.setFirstName("Sevak");
            admin.setLastName("Avoyan");
            admin.setPassword(passwordEncoder.encode("11111111"));
            admin.getRoles().add(UserRole.ROLE_RECTOR);
            admin.getRoles().add(UserRole.ROLE_TEACHER);
            userRepository.save(admin);
        }
    }
}