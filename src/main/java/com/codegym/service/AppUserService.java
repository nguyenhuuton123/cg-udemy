package com.codegym.udemy.service;

import com.codegym.udemy.dto.AppUserDto;
import com.codegym.udemy.entity.AppUser;

public interface AppUserService {
    void saveUser(AppUserDto appUserDto);
    AppUser findByUsername(String username);
    AppUserDto getUserById(Long userId);
    void editUser(Long userId, AppUserDto appUserDto);
    void deleteUser(Long userId);

}
