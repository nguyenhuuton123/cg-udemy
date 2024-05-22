package com.codegym.udemy.dto;

import com.codegym.udemy.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VoucherDto {
    private Long id;
    private String code;
    private Double discountAmount;
    private Date validityStart;
    private Date validityEnd;
    private List<Long> coursesId = new ArrayList<>();
}
