package com.codegym.udemy.dto;

import com.codegym.udemy.entity.Course;
import com.codegym.udemy.entity.Users;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewDto {
    private Long id;
    private Double rating;
    private String comment;
    private Date reviewDate;
    private Long courseId;
    private Long usersId;
}
