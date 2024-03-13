package com.codegym.udemy.service;

import com.codegym.udemy.dto.CourseDto;
import org.springframework.data.domain.Page;

public interface CourseService {
    Page<CourseDto> getCoursesByPage(int pageNumber, int pageSize);
}
