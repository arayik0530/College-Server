package com.lnTime.api.user;

import com.lnTime.dto.user.UserInfoDTO;
import com.lnTime.service.UserService;
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
    public void update(@RequestBody UserInfoDTO user) {
        userService.update(user);
    }
}