package com.sang.toeictest2.Service.Impl;

import com.sang.toeictest2.DTO.Response.AccountDTO;
import com.sang.toeictest2.DTO.Response.ExamDTO;
import com.sang.toeictest2.DTO.Response.ResultDTO;
import com.sang.toeictest2.Entity.*;
import com.sang.toeictest2.Repository.AccountRepository;
import com.sang.toeictest2.Repository.ExamRefQuestionRepository;
import com.sang.toeictest2.Repository.ExamRepository;
import com.sang.toeictest2.Repository.ResultRepository;
import com.sang.toeictest2.Service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamRefQuestionRepository examRefQuestionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    ResultRepository resultRepository;

    @Override
    public ResultDTO submitExam(Long id, List<String> listAnswer) {
        String timeStamp = new SimpleDateFormat("dd-MM-yyy HH:mm:ss").format(Calendar.getInstance().getTime());
        int point = 0, point1 = 0;
        int i = 0;
        Exam exam = this.examRepository.findById(id).get();

        List<ExamRefQuestion> examRefQuestions = this.examRefQuestionRepository.findByExam(exam);
        //List<QuestionDTO> questions = new ArrayList<>();
        for (ExamRefQuestion examRefQuestion : examRefQuestions) {
            Question question = examRefQuestion.getQuestion();
            if (question.getCorectAnswer().equals(listAnswer.get(i))) {
                if (examRefQuestions.size() == 200) {
                    if (i < 100) point++;
                    if (i > 99) point1++;
                } else point++;
            }
            i++;
        }


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Account account = this.accountRepository.findByEmail(username).get();

        Result result = new Result();

        result.setExam(exam);
        result.setAccount(account);
        result.setTotalQuestion(listAnswer.size());
        result.setPoint(point);
        result.setPoint1(point1);
        result.setDatetime(timeStamp);

        this.resultRepository.save(result);

        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setExam(exam);
        resultDTO.setTotalQuestion(exam.getNumberOfQuestion());
        resultDTO.setPoint(point);
        resultDTO.setPoint1(point1);
        resultDTO.setDatetime(timeStamp);

        return resultDTO;
    }

    @Override
    public List<ResultDTO> getExamHistory() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Account account = this.accountRepository.findByEmail(username).get();


        List<Result> results = this.resultRepository.findByAccount(account);
        List<ResultDTO> resultsDTO = new ArrayList<>();

        for(Result result: results){
            ResultDTO resultDTO = new ResultDTO();
            resultDTO.setId(result.getId());
            resultDTO.setExam(result.getExam());
            resultDTO.setTotalQuestion(result.getTotalQuestion());
            resultDTO.setPoint(result.getPoint());
            resultDTO.setPoint1(result.getPoint1());
            resultDTO.setDatetime(result.getDatetime());
            resultsDTO.add(resultDTO);
        }

        return resultsDTO;
    }
}
