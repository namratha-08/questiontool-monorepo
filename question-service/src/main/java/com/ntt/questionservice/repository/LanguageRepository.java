package com.ntt.questionservice.repository;

import com.ntt.questionservice.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    Optional<Language> findByName(String name);
}
