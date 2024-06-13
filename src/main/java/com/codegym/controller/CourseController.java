package com.codegym.t2dshop.controller;

import com.codegym.t2dshop.entity.Course;
import com.codegym.t2dshop.repository.CourseRepository;
import com.codegym.t2dshop.service.CourseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    @GetMapping("/query")
    public ResponseEntity<List<Course>> suggestionsCourses(@RequestParam("search") String searchValue) {
        List<Course> courses = courseService.suggestionsCourses(searchValue);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Course>> searchCourses(@RequestParam("query") String searchValue) {
        List<Course> courses = courseService.findCoursesWithKeyword(searchValue);
        return ResponseEntity.ok(courses);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getCourses() {
        List<Course> courses = courseRepository.findAll();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PostMapping
    public Course createCourse(@RequestParam("video") MultipartFile video, @RequestParam("product") String productJson) throws IOException {
        // Chuyển đổi chuỗi JSON thành đối tượng Course
        ObjectMapper objectMapper = new ObjectMapper();
        Course course = objectMapper.readValue(productJson, Course.class);

        // Chuyển đổi file video thành chuỗi base64
        String videoBase64 = Base64.getEncoder().encodeToString(video.getBytes());
        course.setVideo(videoBase64);

        return courseRepository.save(course);
    }


    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        return courseRepository.findById(id)
                .map(course -> {
                    course.setName(updatedCourse.getName());
                    course.setVideo(updatedCourse.getVideo());
                    course.setPrice(updatedCourse.getPrice());
                    course.setCategory(updatedCourse.getCategory());
                    return courseRepository.save(course);
                })
                .orElseGet(() -> {
                    updatedCourse.setId(id);
                    return courseRepository.save(updatedCourse);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
    }

    @GetMapping("/courses-by-category")
    public List<Course> displayListCourseByCategory(@RequestParam String category) {
        return courseRepository.findByCategory(category);
    }

    @GetMapping("/{id}")
    public Optional<Course> getCourseById(@PathVariable Long id) {
        return courseRepository.findById(id);
    }

    @GetMapping("/best-selling")
    public List<Course> displayListCourseByBestSelling() {
        return courseRepository.findByBestSelling();
    }
}
