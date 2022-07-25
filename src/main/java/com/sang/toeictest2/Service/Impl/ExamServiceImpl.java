package com.sang.toeictest2.Service.Impl;

import com.sang.toeictest2.DTO.Response.ExamDTO;
import com.sang.toeictest2.DTO.Response.QuestionDTO;
import com.sang.toeictest2.Entity.Exam;
import com.sang.toeictest2.Entity.ExamRefQuestion;
import com.sang.toeictest2.Entity.Question;
import com.sang.toeictest2.Repository.ExamRefQuestionRepository;
import com.sang.toeictest2.Repository.ExamRepository;
import com.sang.toeictest2.Service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamRefQuestionRepository examRefQuestionRepository;



    //private final QuestionService questionService;


    @Override
    public ExamDTO getExamUser(Long id) {
        //List<Exam> exams = this.examRepository.findAll();
        //Exam exam = exams.get(1);

        Exam exam = this.examRepository.getById(id);
        ExamDTO examDTO = new ExamDTO();
        examDTO.setId(exam.getId());
        examDTO.setName(exam.getName());
        examDTO.setTime(exam.getExamTime());
        List<ExamRefQuestion> examRefQuestions = this.examRefQuestionRepository.findByExam(exam);
        List<QuestionDTO> questions = new ArrayList<>();
        for (ExamRefQuestion examRefQuestion : examRefQuestions) {
            Question question = examRefQuestion.getQuestion();
            questions.add(new QuestionDTO(question));
        }
        examDTO.setQuestions(questions);
        return examDTO;
    }

    @Override
    public List<Exam> getListExam() {
        return this.examRepository.findAll();
    }


}
