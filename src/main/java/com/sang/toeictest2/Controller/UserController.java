package com.sang.toeictest2.Controller;

import com.sang.toeictest2.DTO.Response.ExamDTO;
import com.sang.toeictest2.DTO.Response.ResultDTO;
import com.sang.toeictest2.Entity.Exam;
import com.sang.toeictest2.Service.ExamService;
import com.sang.toeictest2.Service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private ExamService examService;

    @Autowired
    private ResultService resultService;

//    @GetMapping("/exam")
//    private ResponseEntity<?> getExamUser(){
//        ExamDTO exam =this.examService.getExamUser();
//        return new ResponseEntity<>(exam, HttpStatus.OK);
//    }

    @GetMapping("/exam")
    @PreAuthorize("hasRole('USER')||hasRole('ADMIN')")
    public ResponseEntity<?> listExam() {
        List<Exam> exams = this.examService.getListExam();
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @GetMapping("/exam/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getExamUser(@PathVariable Long id) {
        ExamDTO exam = this.examService.getExamUser(id);
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }

    @PostMapping("/exam/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> submitExam(@PathVariable("id") Long id,
                          @RequestBody List<String> listAnswer) {
        ResultDTO result = this.resultService.submitExam(id, listAnswer);
        //int point = this.resultService.submitExam(id, listAnswer);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/file/{folder}/{fileName}")
    public ResponseEntity<?> getFile(@PathVariable("folder") String folder,@PathVariable("fileName") String fileName) throws FileNotFoundException {
        File file = new File("C:\\MyWebData\\"+folder+"\\"+fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping ("/history")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> listResult(){
        List<ResultDTO> results = this.resultService.getExamHistory();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}