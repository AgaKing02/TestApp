package com.example.onlinevote.multithreading;

import com.example.onlinevote.dto.ResultDto;
import com.example.onlinevote.models.Result;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class RecountService implements Recount {
    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Override
    public ResultDto recount(Result result,Integer answerNumber) {
        Future<ResultDto> submit = executorService.submit(new RecountTask(result, answerNumber));
        try {
            return submit.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
