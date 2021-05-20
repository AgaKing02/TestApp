package com.example.onlinevote.repositories;

import com.example.onlinevote.models.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
    List<Question> findByQuizId(int id);
    Question getById(int id);
}
