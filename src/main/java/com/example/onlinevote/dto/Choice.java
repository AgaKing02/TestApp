package com.example.onlinevote.dto;

public class Choice {
    private int questionID;
    private int answerID;

    public Choice(int questionID, int answerID) {
        this.questionID = questionID;
        this.answerID = answerID;
    }

    public Choice() {

    }

    public Choice(int id, String parameter) {
        this.questionID=id;
        this.answerID= Integer.parseInt(parameter);
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "questionID=" + questionID +
                ", answerID=" + answerID +
                '}';
    }
}
