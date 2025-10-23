package com.ntt.questionservice.repository;

import com.ntt.questionservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByLanguageId(Integer languageId);
}
