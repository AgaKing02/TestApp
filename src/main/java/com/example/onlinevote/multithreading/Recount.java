package com.example.onlinevote.multithreading;

import com.example.onlinevote.dto.ResultDto;
import com.example.onlinevote.models.Result;

public interface Recount {
    ResultDto recount(Result result, Integer answer);
}
