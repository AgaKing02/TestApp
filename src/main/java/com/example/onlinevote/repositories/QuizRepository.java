package com.example.onlinevote.repositories;


import com.example.onlinevote.models.Quiz;
import com.example.onlinevote.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuizRepository extends CrudRepository<Quiz, Integer> {
    Iterable<Quiz> findByTag(String filter);
    Iterable<Quiz> getByTagLike(String tag);
    Iterable<Quiz> findAllByAuthorUsername(String author_username);
    Iterable<Quiz> findAllByAuthorUsernameAndTag(String author_username, String tag);
}
