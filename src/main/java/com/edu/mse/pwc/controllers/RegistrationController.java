package com.edu.mse.pwc.controllers;

import com.edu.mse.pwc.dtos.Counter;
import com.edu.mse.pwc.dtos.UserDto;
import com.edu.mse.pwc.persistence.entities.Role;
import com.edu.mse.pwc.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reg")
public class RegistrationController {
    private final UserService userService;

    @PostMapping
    public Counter<UserDto> registration(@RequestBody UserDto newUser) {
        try {
            userService.getUserByUsername(newUser.getUsername());
            return new Counter<UserDto>(HttpStatus.OK.value(), "Username already exist", null);
        } catch (Exception e) {
            newUser.setRole(Role.USER);
            UserDto dto = userService.createUser(newUser);
            return new Counter<UserDto>(HttpStatus.OK.value(), "Registration successful", dto);
        }
    }
}