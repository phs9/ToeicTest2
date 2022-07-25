package com.sang.toeictest2.DTO.Response;

import com.sang.toeictest2.Entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

    private Long id;
    private String thread;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private int part;
    private Long group;
    private String group_thread;
    private String audio;
    private  String picture;

    public QuestionDTO(Question question) {
        this.id = question.getId();
        this.thread = question.getThread();
        this.answerA = question.getAnswerA();
        this.answerB = question.getAnswerB();
        this.answerC = question.getAnswerC();
        this.answerD = question.getAnswerD();
        this.part = question.getPart();
        this.group = question.getGroup();
        this.group_thread = question.getGroup_thread();
        this.audio = question.getAudio();
        this.picture= question.getPicture();
    }
}
