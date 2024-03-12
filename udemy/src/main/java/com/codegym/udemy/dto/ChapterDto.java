package com.codegym.udemy.dto;

import com.codegym.udemy.entity.Course;
import com.codegym.udemy.entity.Lesson;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChapterDto {
    private Long id;
    private String name;
    private Long courseId;
    private List<Long> lessonsId = new ArrayList<>();
}
