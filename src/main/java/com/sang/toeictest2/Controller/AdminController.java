package com.sang.toeictest2.Controller;

import com.sang.toeictest2.DTO.Response.AccountDTO;
import com.sang.toeictest2.DTO.Response.ExamDTO;
import com.sang.toeictest2.Entity.Account;
import com.sang.toeictest2.Entity.Question;
import com.sang.toeictest2.Repository.ResultRepository;
import com.sang.toeictest2.Service.AdminService;
import com.sang.toeictest2.Service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    ExamService examService;

    @Autowired
    ResultRepository resultRepository;

    @GetMapping("/account")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllAccount() {
        List<AccountDTO> listAccount = this.adminService.getAllAccount();
        return new ResponseEntity<>(listAccount, HttpStatus.OK);
    }

    @GetMapping("/account/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAccountById(@PathVariable("id") Long id) {
        AccountDTO accountDTO = this.adminService.getAccountById(id);
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }

    @PostMapping("/account")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        boolean status = this.adminService.createAccount(account);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping("/account/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateAccount(@RequestBody Account accountRe, @PathVariable("id") Long id) {
        boolean status = this.adminService.updateAccount(accountRe, id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @DeleteMapping("/account/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteAccount(@PathVariable("id") Long id) {
        boolean status = this.adminService.deleteAccount(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @DeleteMapping("/exam/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteExam(@PathVariable("id") Long id) {
        boolean status = this.examService.deleteExam(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @DeleteMapping("/examQ/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteExamAndQuestions(@PathVariable("id") Long id) {
        boolean status = this.examService.deleteExamAndQuestions(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping("/exam/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getExamAdmin(@PathVariable("id") Long id) {
        ExamDTO exam = this.examService.getExamAdmin(id);
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }

    @PostMapping("/createExam")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createExam(@RequestBody ExamDTO examDTO) {
        Long id = this.examService.createExam(examDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/updateExam/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateExam(@PathVariable("id") Long id, @RequestBody ExamDTO examDTO) {
        boolean status = this.examService.updateExam(id, examDTO);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PostMapping("/updateExamRefQ/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateExamRefQ(@PathVariable("id") Long id, @RequestBody List<Long> questionIDs) {
        boolean status = this.examService.updateExamRefQ(id, questionIDs);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping("/question")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getListQuestion() {
        List<Question> questions = this.examService.getListQuestion();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PostMapping("/question")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createQuestion(@RequestBody List<Question> questions) {
        boolean status = this.examService.createQuestion(questions);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping("/question/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateQuestion(@PathVariable("id") Long id, @RequestBody Question question) {
        boolean status = this.examService.updateQuestion(id, question);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @DeleteMapping("/question/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") Long id) {
        boolean status = this.examService.deleteQuestion(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PostMapping("/upload/{folder}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> uploadFile(@PathVariable("folder") String folder, @RequestBody MultipartFile[] files) {
        Arrays.asList(files).stream().forEach(file -> {
            try {
                file.transferTo(new File("C:\\MyWebData\\" + folder + "\\", file.getOriginalFilename()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    public class FileObj {
        public String filename;
        public Long size;

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public void setSize(Long size) {
            this.size = size;
        }
    }

    @GetMapping("/file/{folder}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getListFile(@PathVariable("folder") String folder) {
        List<Object> listFile = new ArrayList<>();
        File[] files = new File("C:\\MyWebData\\" + folder).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                FileObj obj = new FileObj();
                obj.setFilename(file.getName());
                obj.setSize(file.length());
                listFile.add(obj);
            }
        }
        return new ResponseEntity<>(listFile, HttpStatus.OK);
    }

    @DeleteMapping("/file/{folder}/{filename}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteFile(@PathVariable("folder") String folder, @PathVariable("filename") String filename) {
        File file = new File("C:\\MyWebData\\" + folder + "\\" + filename);
        file.delete();
        boolean status = true;
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PostMapping("/renameFile/{folder}/{filename}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> renameFile(@PathVariable("folder") String folder, @PathVariable("filename") String filename, @RequestBody String newName) throws IOException {
        //File file = new File("C:\\MyWebData\\" + folder + "\\" + filename);
        Path file = Paths.get("C:\\MyWebData\\" + folder + "\\" + filename);
        Files.move(file, file.resolveSibling(newName.substring(0,newName.length()-1)));
        //File newFileName = new File(file.getParent(), newName);
        boolean status = true;
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    public class DashBoards{
        public int number_of_acc;
        public int number_of_result;
        public int number_of_exam;
        public int number_of_question;
        public int number_of_audio;
        public int number_of_picture;

        public void setNumber_of_acc(int number_of_acc) {
            this.number_of_acc = number_of_acc;
        }

        public void setNumber_of_result(int number_of_result) {
            this.number_of_result = number_of_result;
        }

        public void setNumber_of_exam(int number_of_exam) {
            this.number_of_exam = number_of_exam;
        }

        public void setNumber_of_question(int number_of_question) {
            this.number_of_question = number_of_question;
        }

        public void setNumber_of_audio(int number_of_audio) {
            this.number_of_audio = number_of_audio;
        }

        public void setNumber_of_picture(int number_of_picture) {
            this.number_of_picture = number_of_picture;
        }
    }

    @GetMapping("/dashboards")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> dashboard(){
        DashBoards dashBoards = new DashBoards();
        dashBoards.setNumber_of_acc(this.adminService.getAllAccount().size());
        dashBoards.setNumber_of_result(this.resultRepository.findAll().size());
        dashBoards.setNumber_of_exam(this.examService.getListExam().size());
        dashBoards.setNumber_of_question(this.examService.getListQuestion().size());
        File[] filesA = new File("C:\\MyWebData\\Audio").listFiles();
        File[] filesP = new File("C:\\MyWebData\\Picture").listFiles();
        dashBoards.setNumber_of_audio(filesA.length);
        dashBoards.setNumber_of_picture(filesP.length);
        return new ResponseEntity<>(dashBoards,HttpStatus.OK);
    }
}



