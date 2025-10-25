package com.ntt.questiontool.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {
    private int id;
    private Integer courseId;
    private Integer languageId;
    private String title;
    private String body;
    private String type;
    private String difficulty;
    private String metadata;
    private String createdAt;

    // legacy fields (optional)
    private String questionText;
    private String optionA, optionB, optionC, optionD;
    private String correctAnswer;
    private String language;

    // NEW: options list
    private List<Option> options = new ArrayList<>();

    // --- getters / setters for existing fields ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Integer getCourseId() { return courseId; }
    public void setCourseId(Integer courseId) { this.courseId = courseId; }
    public Integer getLanguageId() { return languageId; }
    public void setLanguageId(Integer languageId) { this.languageId = languageId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public String getMetadata() { return metadata; }
    public void setMetadata(String metadata) { this.metadata = metadata; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    // options getter / setter
    public List<Option> getOptions() { return options; }
    public void setOptions(List<Option> options) { this.options = options; }

    // (optional) getters/setters for legacy fields if you used them
    // ...
}
