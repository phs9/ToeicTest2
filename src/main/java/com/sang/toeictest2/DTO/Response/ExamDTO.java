package com.sang.toeictest2.DTO.Response;

import com.sang.toeictest2.Entity.Question;
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
    private String date;
    private Integer numberOfQuestion;
    private List<QuestionDTO> questions;
    private List<Long> questionIDs;
}
