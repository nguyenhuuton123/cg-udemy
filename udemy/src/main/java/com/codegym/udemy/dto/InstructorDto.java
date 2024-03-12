package com.codegym.udemy.dto;

import com.codegym.udemy.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InstructorDto {
    private Long id;
    private int age;
    private int expertise;
    private String bio;
    private Long usersId;
}
