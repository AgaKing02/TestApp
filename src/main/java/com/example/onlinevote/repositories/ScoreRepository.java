package com.example.onlinevote.repositories;

import com.example.onlinevote.models.Group;
import com.example.onlinevote.models.Quiz;
import com.example.onlinevote.models.Score;
import com.example.onlinevote.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends CrudRepository<Score,Integer> {
    List<Score> getAllByQuiz(Quiz quiz);
    void deleteAllByQuiz(Quiz quiz);
    List<Score> getAllByQuizAndUserGroup(Quiz quiz, Group user_group);
    boolean existsByUserAndQuiz(User user, Quiz quiz);
    Score getByUserAndQuiz(User user, Quiz quiz);
    List<Score> getByUserUsername(String user_username);



}
