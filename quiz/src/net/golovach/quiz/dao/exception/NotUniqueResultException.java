package net.golovach.quiz.dao.exception;

public class NotUniqueResultException extends DaoBusinessException {

    public NotUniqueResultException(String message) {
        super(message);
    }

    public NotUniqueResultException(String message, Throwable cause) {
        super(message, cause);
    }
}

