package net.golovach.quiz.dao.tx;

import java.util.concurrent.Callable;

public interface TransactionManager {
    
    public <T> T doInTransaction(Callable<T> unitOfWork) 
            throws Exception;
}

