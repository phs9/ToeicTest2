package com.sang.toeictest2.Service;

import com.sang.toeictest2.DTO.Response.ExamDTO;
import com.sang.toeictest2.Entity.Exam;

import java.util.List;

public interface ExamService {
    ExamDTO getExamUser(Long id);

    List<Exam> getListExam();

}
