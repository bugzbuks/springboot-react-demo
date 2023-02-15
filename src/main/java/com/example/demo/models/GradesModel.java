package com.example.demo.models;

import com.example.demo.utils.ValidScore;
//import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotEmpty;

import java.util.UUID;

public class GradesModel {

    @NotEmpty(message = "First name cannot be blank")
    private String name;

    @NotEmpty(message = "Subject cannot be blank")
    private String subject;

    @ValidScore(message="Score must be 0-100")
    private double score;

    private UUID id;

    public GradesModel(String name, String subject, double score) {
        this.name = name;
        this.subject = subject;
        this.score = score;
        this.id = UUID.randomUUID();
    }

    public GradesModel() {
        this.id = UUID.randomUUID();
        this.name = "";
        this.subject = "";
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
