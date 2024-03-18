package com.codegym.udemy.controller;

import com.codegym.udemy.dto.StudentDto;
import com.codegym.udemy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/create/{userId}", consumes = "multipart/form-data")
    public ResponseEntity<String> createStudent(@PathVariable Long userId,
                                           @ModelAttribute StudentDto studentDto,
                                           @RequestParam("file") MultipartFile file) {
        boolean created = studentService.createStudent(userId, studentDto, file);
        return ResponseEntity.status(created ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<StudentDto> getStudentByUserId(@PathVariable Long userId) {
        StudentDto studentDto = studentService.getStudentByUserId(userId);
        if (studentDto != null) {
            return ResponseEntity.ok(studentDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/edit/{studentId}")
    public ResponseEntity<?> editStudent(@PathVariable Long studentId, @RequestBody StudentDto studentDto, @RequestParam(value = "photo", required = false) MultipartFile photo) {
        boolean edited = studentService.editStudent(studentId, studentDto, photo);
        return ResponseEntity.status(edited ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteStudentByUserId(@PathVariable Long userId) {
        boolean deleted = studentService.deleteStudentByUserId(userId);
        return ResponseEntity.status(deleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND).build();
    }
}
