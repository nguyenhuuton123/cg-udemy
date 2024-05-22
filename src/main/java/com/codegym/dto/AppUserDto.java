package com.codegym.udemy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppUserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private boolean isActive;
}
