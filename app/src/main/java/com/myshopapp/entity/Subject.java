package com.myshopapp.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/16.
 */
public class Subject {
    private String subjectTitle;
    private String startTime,endTime;
    private List<SubjectDetails> subjectDetailses =new ArrayList<>();

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<SubjectDetails> getSubjectDetailses() {
        return subjectDetailses;
    }

    public void setSubjectDetailses(List<SubjectDetails> subjectDetailses) {
        this.subjectDetailses = subjectDetailses;
    }
}
