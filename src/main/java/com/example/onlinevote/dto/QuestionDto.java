package com.example.onlinevote.dto;

import com.example.onlinevote.models.Quiz;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Map;

public class QuestionDto {
    private int id;

    private String text;


    private Map<Integer, String> options;

    private Integer answerNumber;



    public QuestionDto(int id,String text, Map<Integer, String> options) {
        this.id = id;
        this.text=text;
        this.options = options;
    }

    public QuestionDto() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, String> getOptions() {
        return options;
    }

    public void setOptions(Map<Integer, String> options) {
        this.options = options;
    }

    public Integer getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(Integer answerNumber) {
        this.answerNumber = answerNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
