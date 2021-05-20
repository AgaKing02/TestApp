package com.example.onlinevote.multithreading;

import com.example.onlinevote.dto.GroupStat;
import com.example.onlinevote.models.Group;
import com.example.onlinevote.models.Score;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.concurrent.Callable;

import static java.util.stream.Collectors.summarizingDouble;

public class StatisticsTask implements Callable<GroupStat> {
    private final List<Score> scoreList;
    private final Group group;

    public StatisticsTask(List<Score> scoreList, Group group) {
        this.scoreList = scoreList;
        this.group = group;
    }

    @Override
    public GroupStat call() throws Exception {
        return getStatistics();
    }

    public synchronized GroupStat getStatistics() {
        GroupStat groupStat = new GroupStat(group.getName());
        DoubleSummaryStatistics result = scoreList.stream()
                .collect(summarizingDouble(Score::getScore));
        groupStat.setDoubleSummaryStatistics(result);
        return groupStat;
    }
}
