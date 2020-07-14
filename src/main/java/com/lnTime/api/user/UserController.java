package com.lnTime.api.user;

import com.lnTime.dto.user.PasswordChangingDTO;
import com.lnTime.dto.user.UserInfoDTO;
import com.lnTime.service.UserService;
import com.lnTime.service.util.exception.ActionForbiddenException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users/")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("me")
    @PreAuthorize(value = "isAuthenticated()")
    public UserInfoDTO getMe() {
        return userService.findById(userService.getMe());
    }

    @GetMapping("all")
    @PreAuthorize(value = "hasAuthority('ROLE_RECTOR')")
    public Page<UserInfoDTO> findAllUsers(@PageableDefault Pageable pageable) {
        return userService.findAllUsers(pageable).map(UserInfoDTO::mapFromEntity);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize(value = "hasAuthority('ROLE_RECTOR')")
    public void remove(@PathVariable Long id) {
        userService.remove(id);
    }

    @PutMapping("update")
    public void update(@RequestBody UserInfoDTO userInfoDto) {
        if (userInfoDto.getId().equals(userService.getMe())) {
            userService.update(userInfoDto);
        }else {
            throw new ActionForbiddenException();
        }
    }

    @GetMapping("{id}")
    @PreAuthorize(value = "isAuthenticated()")
    public UserInfoDTO getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("change-password")
    public void changePassword(@RequestBody PasswordChangingDTO passwordChangingDto) {
        if (passwordChangingDto.getEmail().equals(userService.findById(userService.getMe()).getMail())) {
            userService.updatePassword(passwordChangingDto);
        }else {
            throw new ActionForbiddenException();
        }
    }
}