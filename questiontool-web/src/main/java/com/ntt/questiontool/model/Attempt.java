package com.ntt.questiontool.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Attempt {
    private int id;
    private int userId;
    private Integer testId; // can be null
    private Timestamp startedAt;
    private Timestamp finishedAt;
    private BigDecimal totalScore;
    private List<AttemptAnswer> answers = new ArrayList<>();

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public Integer getTestId() { return testId; }
    public void setTestId(Integer testId) { this.testId = testId; }
    public Timestamp getStartedAt() { return startedAt; }
    public void setStartedAt(Timestamp startedAt) { this.startedAt = startedAt; }
    public Timestamp getFinishedAt() { return finishedAt; }
    public void setFinishedAt(Timestamp finishedAt) { this.finishedAt = finishedAt; }
    public BigDecimal getTotalScore() { return totalScore; }
    public void setTotalScore(BigDecimal totalScore) { this.totalScore = totalScore; }
    public List<AttemptAnswer> getAnswers() { return answers; }
    public void setAnswers(List<AttemptAnswer> answers) { this.answers = answers; }
    public void addAnswer(AttemptAnswer a) { this.answers.add(a); }
}
