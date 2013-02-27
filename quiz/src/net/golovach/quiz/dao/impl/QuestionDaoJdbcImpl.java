package net.golovach.quiz.dao.impl;

import net.golovach.quiz.dao.AbstractDao;
import net.golovach.quiz.dao.QuestionDao;
import net.golovach.quiz.dao.exception.DaoSystemException;
import net.golovach.quiz.dao.exception.NoSuchEntityException;
import net.golovach.quiz.dao.impl.jdbc.JdbcUtils;
import net.golovach.quiz.entity.Question;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoJdbcImpl extends AbstractDao<Question> implements QuestionDao {
    public static final String SELECT_CONNECTED_WITH_QUIZ
            = "SELECT question_id AS id, caption, question FROM questions_to_quizes, questions WHERE question_id = id AND quiz_id = ";

    @Override
    public Question selectById(int id) throws DaoSystemException, NoSuchEntityException {
        return null;
    }

    @Override
    public List<Question> selectConnectedWithQuiz(int quizId) throws DaoSystemException {
        Connection conn = getSerializableConnection();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(SELECT_CONNECTED_WITH_QUIZ + quizId);
            List<Question> result = new ArrayList<Question>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String caption = rs.getString("caption");
                String question = rs.getString("question");
                Question quiz = new Question(id);
                quiz.setCaption(caption);
                quiz.setQuestion(question);
                result.add(quiz);
            }
            return result;
        } catch (SQLException e) {
            throw new DaoSystemException("Can't execute SQL = '" + SELECT_CONNECTED_WITH_QUIZ + "'", e);
        } finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(statement);
        }
    }
}
