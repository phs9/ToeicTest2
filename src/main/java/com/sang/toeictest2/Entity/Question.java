package com.sang.toeictest2.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String thread;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String corectAnswer;
    private int part;

    @Column(name="`group`")
    private Long group;


    private String audio;
    private String picture;
    private String group_thread;

}
