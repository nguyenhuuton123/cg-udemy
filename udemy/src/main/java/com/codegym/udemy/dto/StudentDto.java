package com.codegym.udemy.dto;

import com.codegym.udemy.entity.AppUser;
import com.codegym.udemy.entity.Course;
import com.codegym.udemy.entity.Review;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class StudentDto {
    private Long id;
    private String name;
    private String fullName;
    private String bio;
    private String photoUrl;
    private String linkTwittwer;
    private String linkFacebook;
    private String linkLinkedIn;
    private String linkYoutube;

    private Long appUserId;
    private List<Long> coursesId = new ArrayList<>();
    private List<Long> reviewsId = new ArrayList<>();
}
