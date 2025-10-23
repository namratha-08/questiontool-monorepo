package com.ntt.questiontool.model;

import java.util.List;

public class Test {
    private int testId;
    private String language;
    private List<Question> questions;
    private int score;

    public int getTestId() { return testId; }
    public void setTestId(int testId) { this.testId = testId; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public List<Question> getQuestions() { return questions; }
    public void setQuestions(List<Question> questions) { this.questions = questions; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}
