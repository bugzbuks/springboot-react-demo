package com.example.demo.models;

import java.util.List;

public class GradeListModel {
    List<GradesModel> grades;

    public GradeListModel(List<GradesModel> grades) {
        this.grades = grades;
    }

    public GradeListModel() {
    }

    public List<GradesModel> getGrades() {
        return grades;
    }

    public void setGrades(List<GradesModel> grades) {
        this.grades = grades;
    }
}
