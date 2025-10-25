package com.ntt.questiontool.dao;

import com.ntt.questiontool.model.Attempt;
import com.ntt.questiontool.model.AttemptAnswer;
import com.ntt.questiontool.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

public class AttemptDAO {

    /**
     * Save attempt row and its answers. Returns generated attempt id (or -1 on error).
     *
     * - attempts table columns: (user_id, test_id, started_at, finished_at, total_score)
     * - attempt_answers: (attempt_id, question_id, answer_text, marks_awarded, created_at)
     *
     * This method uses a transaction: attempt row is inserted first and its generated id used
     * to insert answer rows. On error, rollback.
     */
    public int saveAttemptWithAnswers(Attempt attempt) {
        final String insertAttemptSql =
                "INSERT INTO attempts (user_id, test_id, started_at, finished_at, total_score) VALUES (?, ?, ?, ?, ?)";
        final String insertAnswerSql =
                "INSERT INTO attempt_answers (attempt_id, question_id, answer_text, marks_awarded, created_at) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement psAttempt = conn.prepareStatement(insertAttemptSql, Statement.RETURN_GENERATED_KEYS)) {
                psAttempt.setInt(1, attempt.getUserId());
                if (attempt.getTestId() != null) psAttempt.setInt(2, attempt.getTestId());
                else psAttempt.setNull(2, Types.INTEGER);
                if (attempt.getStartedAt() != null) psAttempt.setTimestamp(3, attempt.getStartedAt());
                else psAttempt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                if (attempt.getFinishedAt() != null) psAttempt.setTimestamp(4, attempt.getFinishedAt());
                else psAttempt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                if (attempt.getTotalScore() != null) psAttempt.setBigDecimal(5, attempt.getTotalScore());
                else psAttempt.setBigDecimal(5, BigDecimal.ZERO);

                int rows = psAttempt.executeUpdate();
                if (rows == 0) throw new SQLException("Inserting attempt failed, no rows affected.");

                int attemptId;
                try (ResultSet keys = psAttempt.getGeneratedKeys()) {
                    if (keys.next()) {
                        attemptId = keys.getInt(1);
                    } else {
                        throw new SQLException("Inserting attempt failed, no ID obtained.");
                    }
                }

                // insert answers
                try (PreparedStatement psAnswer = conn.prepareStatement(insertAnswerSql)) {
                    Timestamp now = new Timestamp(System.currentTimeMillis());
                    List<AttemptAnswer> answers = attempt.getAnswers();
                    for (AttemptAnswer a : answers) {
                        psAnswer.setInt(1, attemptId);
                        psAnswer.setInt(2, a.getQuestionId());
                        psAnswer.setString(3, a.getAnswerText());
                        psAnswer.setBigDecimal(4, a.getMarksAwarded() != null ? a.getMarksAwarded() : BigDecimal.ZERO);
                        psAnswer.setTimestamp(5, now);
                        psAnswer.addBatch();
                    }
                    psAnswer.executeBatch();
                }

                conn.commit();
                return attemptId;
            } catch (Exception e) {
                conn.rollback();
                e.printStackTrace();
                return -1;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}
