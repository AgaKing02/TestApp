package com.example.onlinevote.dto;

import com.example.onlinevote.models.Quiz;

import java.util.ArrayList;
import java.util.List;

public class AnswerSheet {
   private List<QuestionDto> questionDtos=new ArrayList<>();

    public AnswerSheet(Quiz quiz) {
        quiz.getQuestions().forEach(e->questionDtos.add(new QuestionDto(e.getId(),e.getText(),e.getOptions())));
    }

    public List<QuestionDto> getQuestionDtos() {
        return questionDtos;
    }

    public void setQuestionDtos(List<QuestionDto> questionDtos) {
        this.questionDtos = questionDtos;
    }
}
