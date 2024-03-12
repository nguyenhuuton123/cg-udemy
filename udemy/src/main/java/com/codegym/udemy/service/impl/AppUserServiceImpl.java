package com.codegym.udemy.service.impl;

import com.codegym.udemy.constant.VarConstant;
import com.codegym.udemy.dto.AppUserDto;
import com.codegym.udemy.entity.AppUser;
import com.codegym.udemy.entity.Role;
import com.codegym.udemy.repository.AppUserRepository;
import com.codegym.udemy.repository.RoleRepository;
import com.codegym.udemy.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(AppUserDto appUserDto) {
        AppUser appUser = new AppUser();
        appUser.setUsername(appUserDto.getUsername());
        appUser.setPassword(passwordEncoder.encode(appUserDto.getPassword()));
        appUser.setEmail(appUserDto.getEmail());

        Role role = roleRepository.findByRoleType(VarConstant.ROLE_TYPE_USER);
        appUser.setRoles(Arrays.asList(role));
        appUser.setActive(true);
        appUserRepository.save(appUser);
    }

    @Override
    public AppUser findByUsername(String username) {
        return null;
    }
}
