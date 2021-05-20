package com.example.onlinevote.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Appeal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    private boolean checked;

    private String proof;
}