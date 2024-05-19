package com.codegym.udemy.dto;

import com.codegym.udemy.entity.Chapter;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LessonDto {
    private Long id;
    private String name;
    private String videoUrl;
    private Long chapterId;
}
