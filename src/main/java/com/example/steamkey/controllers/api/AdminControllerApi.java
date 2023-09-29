package com.example.steamkey.controllers.api;

import com.example.steamkey.models.User;
import com.example.steamkey.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController

@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminControllerApi {
    private final UserService userService;

    @GetMapping(value = "/api/admin", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<User> getUsers(Principal principal) {
        return userService.list();
    }

    @PostMapping("/api/admin/user/ban/{id}")
    public void banUser(@PathVariable("id") Long id) {
        userService.banUser(id);
    }

    @GetMapping("/api/admin/user/edit/{user}")
    public User getUser(@PathVariable("user") User user, Principal principal) {
        return user;
    }

    @PostMapping("/api/admin/user/edit")
    public void editUserRoles(@RequestParam("userId") User user, @RequestParam Map<String, String> form) {
        userService.changeUserRoles(user, form);
    }
}