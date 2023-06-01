package com.Abdessalam.friendMA.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.Abdessalam.friendMA.dto.model.UserDto;
import com.Abdessalam.friendMA.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/test")
    public UserRecord test(Principal principal) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().getUser(principal.getName());
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto user) {
        return userService.save(user);
    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Iterable<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{username}")
    public UserDto getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

}
