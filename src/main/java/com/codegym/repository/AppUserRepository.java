package com.codegym.udemy.repository;

import com.codegym.udemy.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);
    AppUser findByUsername(String username);
    AppUser findFirstByUsername(String username);
    Optional<AppUser> findById(Long id);
    @Query("SELECT u FROM AppUser u WHERE u NOT IN (SELECT ur FROM AppUser ur JOIN ur.roles r WHERE r.roleType = 'ROLE_ADMIN')")
    List<AppUser> findAllNonAdminUsers();
}
