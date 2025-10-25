package com.ntt.questiontool.dao;

import com.ntt.questiontool.model.Option;
import com.ntt.questiontool.model.Question;
import com.ntt.questiontool.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDAO {

    /**
     * Return questions for a language name (e.g. "Java").
     * This method:
     *  1) finds the language id from languages table
     *  2) queries questions for that language_id
     *  3) for each question fetches its options and attaches them to Question.options
     */
    public List<Question> getQuestionsByLanguage(String languageName) {
        List<Question> questions = new ArrayList<>();

        String languageSql = "SELECT id FROM languages WHERE name = ?";
        String questionsSql = "SELECT id, course_id, language_id, title, body, type, difficulty, metadata, created_at FROM questions WHERE language_id = ?";
        String optionsSql = "SELECT id, question_id, option_text, is_correct FROM question_options WHERE question_id = ? ORDER BY id";

        try (Connection conn = DBConnection.getConnection()) {

            // 1) find language id
            Integer languageId = null;
            try (PreparedStatement pst = conn.prepareStatement(languageSql)) {
                pst.setString(1, languageName);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        languageId = rs.getInt("id");
                    }
                }
            }

            if (languageId == null) {
                // no such language -> return empty list
                return questions;
            }

            // 2) fetch questions
            try (PreparedStatement pstQ = conn.prepareStatement(questionsSql)) {
                pstQ.setInt(1, languageId);
                try (ResultSet rsQ = pstQ.executeQuery()) {
                    while (rsQ.next()) {
                        Question q = new Question();
                        q.setId(rsQ.getInt("id"));
                        // these columns may be NULLable
                        int courseId = rsQ.getInt("course_id");
                        if (!rsQ.wasNull()) q.setCourseId(courseId);

                        int langId = rsQ.getInt("language_id");
                        if (!rsQ.wasNull()) q.setLanguageId(langId);

                        q.setTitle(rsQ.getString("title"));
                        q.setBody(rsQ.getString("body"));
                        q.setType(rsQ.getString("type"));
                        q.setDifficulty(rsQ.getString("difficulty"));
                        q.setMetadata(rsQ.getString("metadata"));
                        q.setCreatedAt(rsQ.getString("created_at"));

                        // 3) load options for this question
                        List<Option> options = new ArrayList<>();
                        try (PreparedStatement pstO = conn.prepareStatement(optionsSql)) {
                            pstO.setInt(1, q.getId());
                            try (ResultSet rsO = pstO.executeQuery()) {
                                while (rsO.next()) {
                                    Option o = new Option();
                                    o.setId(rsO.getInt("id"));
                                    o.setQuestionId(rsO.getInt("question_id"));
                                    o.setOptionText(rsO.getString("option_text"));
                                    o.setCorrect(rsO.getInt("is_correct") == 1);
                                    options.add(o);
                                }
                            }
                        }
                        q.setOptions(options);
                        questions.add(q);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // returning whatever we have (maybe empty)
        }

        return questions;
    }
}
