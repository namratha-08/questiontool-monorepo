package com.ntt.questiontool.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class AttemptAnswer {
    private int id;
    private int attemptId;
    private int questionId;
    private String answerText;
    private BigDecimal marksAwarded;
    private Timestamp createdAt;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getAttemptId() { return attemptId; }
    public void setAttemptId(int attemptId) { this.attemptId = attemptId; }
    public int getQuestionId() { return questionId; }
    public void setQuestionId(int questionId) { this.questionId = questionId; }
    public String getAnswerText() { return answerText; }
    public void setAnswerText(String answerText) { this.answerText = answerText; }
    public BigDecimal getMarksAwarded() { return marksAwarded; }
    public void setMarksAwarded(BigDecimal marksAwarded) { this.marksAwarded = marksAwarded; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
