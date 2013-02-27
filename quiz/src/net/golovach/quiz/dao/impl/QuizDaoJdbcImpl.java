package net.golovach.quiz.dao.impl;

import net.golovach.quiz.dao.AbstractDao;
import net.golovach.quiz.dao.QuestionDao;
import net.golovach.quiz.dao.QuizDao;
import net.golovach.quiz.dao.exception.DaoSystemException;
import net.golovach.quiz.dao.exception.NoSuchEntityException;
import net.golovach.quiz.dao.impl.jdbc.JdbcUtils;
import net.golovach.quiz.entity.Question;
import net.golovach.quiz.entity.Quiz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuizDaoJdbcImpl extends AbstractDao<Quiz> implements QuizDao {
    public static final String SELECT_ALL_SQL = "SELECT id, caption, description FROM quizes";

    private QuestionDao questionDao;

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public Quiz selectById(int id) throws DaoSystemException, NoSuchEntityException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Quiz> selectAll() throws DaoSystemException {
        Connection conn = getSerializableConnection();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(SELECT_ALL_SQL);
            List<Quiz> result = new ArrayList<Quiz>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String caption = rs.getString("caption");
                String description = rs.getString("description");
                List<Question> questions = questionDao.selectConnectedWithQuiz(id);
                Quiz quiz = new Quiz(id);
                quiz.setCaption(caption);
                quiz.setDescription(description);
                quiz.setQuestions(questions);
                result.add(quiz);
            }
            return result;
        } catch (SQLException e) {
            throw new DaoSystemException("Can't execute SQL = '" + SELECT_ALL_SQL + "'", e);
        } finally {
            JdbcUtils.closeQuietly(rs);
            JdbcUtils.closeQuietly(statement);
        }
    }
}
