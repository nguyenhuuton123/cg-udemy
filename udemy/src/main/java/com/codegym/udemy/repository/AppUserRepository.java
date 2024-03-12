package com.codegym.udemy.repository;

import com.codegym.udemy.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);
    AppUser findByUsername(String username);
    AppUser findFirstByUsername(String username);
    @Query("SELECT u FROM AppUser u WHERE u NOT IN (SELECT ur FROM AppUser ur JOIN ur.roles r WHERE r.roleType = 'ROLE_ADMIN')")
    List<AppUser> findAllNonAdminUsers();
}
