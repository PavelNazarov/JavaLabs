package net.golovach.quiz.dao;

import net.golovach.quiz.dao.exception.DaoSystemException;
import net.golovach.quiz.dao.exception.user.NotUniqueUserEmailException;
import net.golovach.quiz.dao.exception.user.NotUniqueUserLoginException;
import net.golovach.quiz.entity.User;

import java.util.List;

public interface UserDao {

    public List<User> selectAll() throws DaoSystemException;

    public int deleteById(int id) throws DaoSystemException;

    public void insert(User user) throws NotUniqueUserLoginException, NotUniqueUserEmailException, DaoSystemException;
}
