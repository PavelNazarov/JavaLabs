package net.golovach.quiz.dao.exception.user;

import net.golovach.quiz.dao.exception.DaoBusinessException;

public class NotUniqueUserLoginException extends DaoBusinessException {

    public NotUniqueUserLoginException(String message) {
        super(message);
    }

    public NotUniqueUserLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
