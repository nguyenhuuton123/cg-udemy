package com.codegym.udemy.controller;

import com.codegym.udemy.dto.AppUserDto;
import com.codegym.udemy.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private AppUserService appUserService;
    @Autowired
    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/save")
    public void createUser(@RequestBody AppUserDto appUserDto) {
        appUserService.saveUser(appUserDto);
    }
}
