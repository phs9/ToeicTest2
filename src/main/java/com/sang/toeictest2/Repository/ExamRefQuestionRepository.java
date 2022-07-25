package com.sang.toeictest2.Repository;

import com.sang.toeictest2.Entity.Exam;
import com.sang.toeictest2.Entity.ExamRefQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRefQuestionRepository extends JpaRepository<ExamRefQuestion, Long> {
    List<ExamRefQuestion> findByExam(Exam exam);
}