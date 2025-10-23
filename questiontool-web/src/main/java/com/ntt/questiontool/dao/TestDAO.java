package com.ntt.questiontool.dao;

import com.ntt.questiontool.model.Question;
import com.ntt.questiontool.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDAO {
    public List<Question> getQuestionsByLanguage(String language) {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM questions WHERE language=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, language);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Question q = new Question();
                q.setId(rs.getInt("id"));
                q.setQuestionText(rs.getString("questionText"));
                q.setOptionA(rs.getString("optionA"));
                q.setOptionB(rs.getString("optionB"));
                q.setOptionC(rs.getString("optionC"));
                q.setOptionD(rs.getString("optionD"));
                q.setCorrectAnswer(rs.getString("correctAnswer"));
                q.setLanguage(language);
                questions.add(q);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
