package net.golovach.quiz.dao.impl.extractor;

import net.golovach.quiz.entity.Question;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuizExtractor extends Extractor<Question> {

    public Question extractOne(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String caption = rs.getString("caption");
        String question = rs.getString("question");
        String answerExplanation = rs.getString("answer_explanation");
        Question result = new Question(id);
        result.setCaption(caption);
        result.setExplanation(answerExplanation);
        result.setQuestion(question);
        result.setAnswers(null);
        return result;
    }
}
