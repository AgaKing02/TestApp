package com.example.onlinevote.models;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Length(max = 5000, message = "Too long description!")
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ElementCollection
    @CollectionTable(name = "option")
    @MapKeyColumn(name = "number")
    @Column(name = "description")
    private Map<Integer, String> options;

    private Integer answerNumber;

    private static int ORDER = 0;

    public Question(String text, Quiz quiz) {
        this.text = text;
        this.quiz = quiz;
        this.options = new HashMap<>();
    }

    public Question() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Map<Integer, String> getOptions() {
        return options;
    }

    public void setOptions(Map<Integer, String> options) {
        this.options = options;
    }

    public Integer getAnswerNumber() {
        return this.answerNumber;
    }

    public void setAnswerNumber(Integer answerNumber) {
        this.answerNumber = answerNumber;
    }

    public void addVariant(String variant) {
        ORDER++;
        this.options.put(ORDER, variant);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return getId() == question.getId() && Objects.equals(getText(), question.getText()) && Objects.equals(getOptions(), question.getOptions()) && Objects.equals(getAnswerNumber(), question.getAnswerNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getText(), getOptions(), getAnswerNumber());
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", options=" + options +
                ", answerNumber=" + answerNumber +
                '}';
    }
}
