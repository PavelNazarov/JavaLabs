package net.golovach.quiz.example;

import net.golovach.quiz.dao.impl.QuestionDaoJdbcImpl;
import net.golovach.quiz.dao.impl.QuizDaoJdbcImpl;
import net.golovach.quiz.dao.impl.jdbc.ConnectionFactoryFactory;
import net.golovach.quiz.dao.tx.TransactionManager;
import net.golovach.quiz.dao.tx.TransactionManagerImpl;
import net.golovach.quiz.entity.Quiz;

import java.util.List;
import java.util.concurrent.Callable;

public class SimpleQuizExample {

    public static void main(String[] args) throws Exception {
//        ConnectionFactoryFactory.setType(ConnectionFactoryFactory.FactoryType.C3P0);

        final QuizDaoJdbcImpl quizDao = new QuizDaoJdbcImpl();
        quizDao.setQuestionDao(new QuestionDaoJdbcImpl());
        TransactionManager txManager = new TransactionManagerImpl();

        List<Quiz> quizLists = txManager.doInTransaction(new Callable<List<Quiz>>() {
            public List<Quiz> call() throws Exception {
                quizDao.selectAll();
                quizDao.selectAll();
                quizDao.selectAll();
                quizDao.selectAll();
                return quizDao.selectAll();
            }
        });

        System.out.println("ALL CURRENT QUIZES:");
        for (Quiz quiz : quizLists) {
            System.out.println(quiz.toString());
        }

        ConnectionFactoryFactory.close();
    }
}

