package com.example.onlinevote.multithreading;

import com.example.onlinevote.dto.GroupStat;
import com.example.onlinevote.models.Group;
import com.example.onlinevote.models.Quiz;
import com.example.onlinevote.models.Score;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class StatisticsService implements Statistics {
    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    @Override
    public GroupStat getStatByQuiz(List<Score> scores, Group group) {
        Future<GroupStat> submit = executorService.submit(new StatisticsTask(scores, group));
        try {
            return submit.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;

    }
}
