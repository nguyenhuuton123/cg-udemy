package com.codegym.udemy.repository;

import com.codegym.udemy.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
Optional<Instructor> getInstructorByAppUser_Id(Long appUserId);
}
