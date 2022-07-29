package com.sang.toeictest2.Service;

import com.sang.toeictest2.DTO.Response.ExamDTO;
import com.sang.toeictest2.Entity.Exam;
import com.sang.toeictest2.Entity.Question;

import java.util.List;

public interface ExamService {
    ExamDTO getExamUser(Long id);

    ExamDTO getExamAdmin(Long id);

    List<Exam> getListExam();

    boolean deleteExam(Long id);

    boolean deleteExamAndQuestions(Long id);

    boolean updateExamRefQ(Long id, List<Long> questionIDs);

    Long createExam(ExamDTO examDTO);

    boolean updateExam(Long id, ExamDTO examDTO);

    List<Question> getListQuestion();

    boolean createQuestion(List<Question> questions);

    boolean updateQuestion(Long id, Question question);

    boolean deleteQuestion(Long id);

}
