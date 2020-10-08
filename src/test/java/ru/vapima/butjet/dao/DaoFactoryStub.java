package ru.vapima.butjet.dao;

import ru.vapima.butjet.dao.impl.*;

public class DaoFactoryStub extends DaoFactory {

     public PersonDaoJDBCStub createPersonDao() {
        return new PersonDaoJDBCStub();
    }

    public PlanDaoJDBCStub createPlanDao() {
        return new PlanDaoJDBCStub();
    }

    public AccDaoJDBCStub createAccDao() {
        return new AccDaoJDBCStub();
    }

    public AccInfoDAO createAccInfoDao(){ return new AccInfoDaoJDBCStub();}


}