package com.example.onlinevote.models;

import javax.persistence.*;

/*
 * Participant Answers(Statistics)*/
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private Integer answer;


    public Result(User user, Question question, Integer answer) {
        this.user = user;
        this.question = question;
        this.answer = answer;
    }

    public Result() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Integer getAnswer() {
        return this.answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    public boolean isTrue() {
        return this.question.getAnswerNumber().equals(this.answer);
    }
}
