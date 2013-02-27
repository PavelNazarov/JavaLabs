package net.golovach.quiz.dao.exception.user;

import net.golovach.quiz.dao.exception.DaoBusinessException;

public class NotUniqueUserEmailException extends DaoBusinessException {

    public NotUniqueUserEmailException(String message) {
        super(message);
    }

    public NotUniqueUserEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
