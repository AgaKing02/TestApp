package com.example.onlinevote.repositories;

import com.example.onlinevote.models.Appeal;
import com.example.onlinevote.models.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppealRepository extends CrudRepository<Appeal, Long> {
    List<Appeal> getAllByCheckedFalseAndQuestionQuizAuthorUsername(String question_quiz_author_username);

    boolean existsByQuestionAndCheckedTrue(Question question);
}
