package com.ntt.questionservice.service;

import com.ntt.questionservice.model.Language;
import com.ntt.questionservice.model.Question;
import com.ntt.questionservice.repository.LanguageRepository;
import com.ntt.questionservice.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final LanguageRepository languageRepository;

    public QuestionService(QuestionRepository questionRepository, LanguageRepository languageRepository) {
        this.questionRepository = questionRepository;
        this.languageRepository = languageRepository;
    }

    public List<Question> getQuestionsByLanguageName(String languageName) {
        if (languageName == null || languageName.isBlank()) return Collections.emptyList();

        Optional<Language> langOpt = languageRepository.findByName(languageName);
        if (langOpt.isEmpty()) {
            // No matching language found
            return Collections.emptyList();
        }
        Integer langId = langOpt.get().getId();
        return questionRepository.findByLanguageId(langId);
    }

    // keep any existing methods if needed
}
