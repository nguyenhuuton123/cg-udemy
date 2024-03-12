package com.codegym.udemy.service.impl;

import com.codegym.udemy.dto.InstructorDto;
import com.codegym.udemy.repository.InstructorRepository;
import com.codegym.udemy.service.InstructorService;
import org.springframework.stereotype.Service;

@Service
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public void saveInstructor(InstructorDto instructorDto) {

    }
}
