package net.golovach.quiz.dao.impl.extractor;

import net.golovach.quiz.entity.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuizAnswerExtractor extends Extractor<Answer> {

    @Override
    public Answer extractOne(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String answer = rs.getString("answer");
        boolean correct = rs.getBoolean("correct");
        return new Answer(id, answer, correct);
    }
}
