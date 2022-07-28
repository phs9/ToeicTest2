package com.sang.toeictest2.Service;

import com.sang.toeictest2.DTO.Response.ExamDTO;
import com.sang.toeictest2.Entity.Exam;

import java.util.List;

public interface ExamService {
    ExamDTO getExamUser(Long id);

    ExamDTO getExamAdmin(Long id);

    List<Exam> getListExam();

    boolean deleteExam(Long id);

    boolean deleteExamAndQuestions(Long id);

    boolean updateExamRefQ(Long id, List<Long> questionIDs);

}
