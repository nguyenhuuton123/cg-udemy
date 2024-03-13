package com.codegym.udemy.service;

import com.codegym.udemy.dto.InstructorDto;

public interface InstructorService {
    void saveInstructor(Long userId, InstructorDto instructorDto);
    InstructorDto getInstructorByUserId(Long userId);
    void editInstructor(Long instructorId, InstructorDto instructorDto);
    void deleteInstructorByUserId(Long userId);

}
