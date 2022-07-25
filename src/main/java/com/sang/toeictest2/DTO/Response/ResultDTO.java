package com.sang.toeictest2.DTO.Response;

import com.sang.toeictest2.Entity.Exam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {
    private Long id;
    private Integer totalQuestion;
    private Integer point;
    private Integer point1;
    private Exam exam;
    private String datetime;

}
