package com.sang.toeictest2.Service.Impl;

import com.sang.toeictest2.DTO.Response.ExamDTO;
import com.sang.toeictest2.DTO.Response.QuestionDTO;
import com.sang.toeictest2.Entity.Exam;
import com.sang.toeictest2.Entity.ExamRefQuestion;
import com.sang.toeictest2.Entity.Question;
import com.sang.toeictest2.Entity.Result;
import com.sang.toeictest2.Repository.ExamRefQuestionRepository;
import com.sang.toeictest2.Repository.ExamRepository;
import com.sang.toeictest2.Repository.QuestionRepository;
import com.sang.toeictest2.Repository.ResultRepository;
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

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ResultRepository resultRepository;

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
        examDTO.setDate(exam.getDate());
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
    public ExamDTO getExamAdmin(Long id) {
        Exam exam = this.examRepository.getById(id);
        ExamDTO examDTO = new ExamDTO();
        examDTO.setId(exam.getId());
        examDTO.setName(exam.getName());
        examDTO.setTime(exam.getExamTime());
        examDTO.setDate(exam.getDate());
        examDTO.setNumberOfQuestion(exam.getNumberOfQuestion());
        List<ExamRefQuestion> examRefQuestions = this.examRefQuestionRepository.findByExam(exam);
        List<Long> questionIDs = new ArrayList<>();
        for (ExamRefQuestion examRefQuestion : examRefQuestions) {
            Long questionID = examRefQuestion.getQuestion().getId();
            questionIDs.add(questionID);
        }
        examDTO.setQuestionIDs(questionIDs);
        return examDTO;
    }

    @Override
    public List<Exam> getListExam() {
        return this.examRepository.findAll();
    }

    @Override
    public boolean deleteExam(Long id) {
        Exam exam = examRepository.getById(id);
        List<ExamRefQuestion> examRefQuestions = examRefQuestionRepository.findByExam(exam);
        List<Result> results = resultRepository.findByExam(exam);
        resultRepository.deleteAll(results);
        examRefQuestionRepository.deleteAll(examRefQuestions);
        examRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteExamAndQuestions(Long id) {
        Exam exam = examRepository.getById(id);
        List<ExamRefQuestion> examRefQuestions = examRefQuestionRepository.findByExam(exam);
        List<Question> questions = new ArrayList<>();
        for (ExamRefQuestion examRefQuestion : examRefQuestions) {
            Question question = examRefQuestion.getQuestion();
            questions.add(question);
        }
        List<Result> results = resultRepository.findByExam(exam);
        resultRepository.deleteAll(results);
        examRefQuestionRepository.deleteAll(examRefQuestions);
        questionRepository.deleteAll(questions);
        examRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateExamRefQ(Long id, List<Long> questionIDs) {
        Exam exam = this.examRepository.getById(id);
        List<ExamRefQuestion> examRefQuestions = examRefQuestionRepository.findByExam(exam);
        List<ExamRefQuestion> examRefQuestionsNew = new ArrayList<>();
        if (!questionIDs.isEmpty()) {
            for (Long item : questionIDs) {
                ExamRefQuestion examRefQuestion = new ExamRefQuestion();
                examRefQuestion.setExam(examRepository.getById(id));
                examRefQuestion.setQuestion(questionRepository.getById(item));
                examRefQuestionsNew.add(examRefQuestion);
            }
            examRefQuestionRepository.deleteAll(examRefQuestions);
            examRefQuestionRepository.saveAll(examRefQuestionsNew);
        } else examRefQuestionRepository.deleteAll(examRefQuestions);
        return true;
    }

}
