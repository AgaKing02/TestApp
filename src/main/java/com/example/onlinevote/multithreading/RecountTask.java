package com.example.onlinevote.multithreading;

import com.example.onlinevote.dto.ResultDto;
import com.example.onlinevote.models.Result;

import java.util.concurrent.Callable;

public class RecountTask implements Callable<ResultDto> {
    private final Result result;
    private final Integer answerNumber;


    public RecountTask(Result result, Integer answerNumber) {
        this.result = result;
        this.answerNumber = answerNumber;
    }


    public synchronized ResultDto recount() {
        return new ResultDto(result.getUser(), result.getAnswer().equals(answerNumber));
    }


    @Override
    public ResultDto call() throws Exception {
        return recount();
    }

    public Integer getAnswerNumber() {
        return answerNumber;
    }
}
