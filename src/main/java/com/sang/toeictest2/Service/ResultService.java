package com.sang.toeictest2.Service;

import com.sang.toeictest2.DTO.Response.ResultDTO;
import com.sang.toeictest2.Entity.Result;

import java.util.List;

public interface ResultService {
    ResultDTO submitExam(Long id, List<String> listAnswer);

    List<ResultDTO> getExamHistory();
}
