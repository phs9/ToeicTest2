package com.sang.toeictest2.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamDTO {
    private Long id;
    private String name;
    private Integer time;
    private List<QuestionDTO> questions;
}
