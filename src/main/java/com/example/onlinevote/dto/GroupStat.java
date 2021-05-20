package com.example.onlinevote.dto;

import java.util.DoubleSummaryStatistics;

public class GroupStat {
    public String name;
    public DoubleSummaryStatistics doubleSummaryStatistics;

    public GroupStat(){

    }

    public GroupStat(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DoubleSummaryStatistics getDoubleSummaryStatistics() {
        return doubleSummaryStatistics;
    }

    public void setDoubleSummaryStatistics(DoubleSummaryStatistics doubleSummaryStatistics) {
        this.doubleSummaryStatistics = doubleSummaryStatistics;
    }

}

