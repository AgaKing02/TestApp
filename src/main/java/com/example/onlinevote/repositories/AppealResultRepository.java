package com.example.onlinevote.repositories;

import com.example.onlinevote.models.Appeal;
import com.example.onlinevote.models.AppealResult;
import com.example.onlinevote.models.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppealResultRepository extends CrudRepository<AppealResult, Long> {
    List<AppealResult> getAllByAppealUserUsername(String appeal_user_username);
    AppealResult getByAppeal(Appeal appeal);
}
