package com.example.onlinevote.multithreading;

import com.example.onlinevote.dto.GroupStat;
import com.example.onlinevote.models.Group;
import com.example.onlinevote.models.Quiz;
import com.example.onlinevote.models.Score;

import java.util.List;

public interface Statistics {
GroupStat getStatByQuiz(List<Score> scores, Group group);
}
