package com.codegym.udemy.controller;

import com.codegym.udemy.dto.AppUserDto;
import com.codegym.udemy.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<String> createUser(@RequestBody AppUserDto appUserDto) {
        appUserService.saveUser(appUserDto);
        return ResponseEntity.ok("User created successfully");
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        try {
            appUserService.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("update/{userId}")
    public ResponseEntity<String> editUser(@PathVariable Long userId, @RequestBody AppUserDto appUserDto) {
        try {
            appUserService.editUser(userId, appUserDto);
            return ResponseEntity.ok("User updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
