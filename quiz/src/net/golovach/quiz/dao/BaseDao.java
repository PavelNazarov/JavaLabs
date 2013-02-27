package net.golovach.quiz.dao;

import net.golovach.quiz.dao.exception.DaoBusinessException;
import net.golovach.quiz.dao.exception.DaoSystemException;
import net.golovach.quiz.dao.exception.NoSuchEntityException;

import java.util.List;

public interface BaseDao<T> {

    public T selectById(int id) throws DaoSystemException, NoSuchEntityException;

    public List<T> selectAll() throws DaoSystemException;

//    public void insert(T newEntity) throws DaoSystemException, DaoBusinessException;
//
//    public void update(T newEntity) throws DaoSystemException, DaoBusinessException;
//
//    public void deleteById(int id) throws DaoSystemException, NoSuchEntityException;
}
