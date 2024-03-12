package com.codegym.udemy.repository;

import com.codegym.udemy.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

}
