package com.ntt.questionservice.controller;

import com.ntt.questionservice.model.Question;
import com.ntt.questionservice.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // Accepts a language name like "Java"
    @GetMapping("/{languageName}")
    public List<Question> getQuestions(@PathVariable("languageName") String languageName) {
        return questionService.getQuestionsByLanguageName(languageName);
    }
}
