package ru.vapima.butjet.dao;


import ru.vapima.butjet.dao.impl.*;

public class DaoFactory {
    public PersonDAO createPersonDao() {
        return new PersonDaoJDBC(JDBCpoolJNDI.getDataSource());
    }

    public PlanDAO createPlanDao() {
        return new PlanDaoJDBC(JDBCpoolJNDI.getDataSource());
    }

    public AccDAO createAccDao() {
        return new AccDaoJDBC(JDBCpoolJNDI.getDataSource());
    }

    public AccInfoDAO createAccInfoDao() {
        return new AccInfoDaoJDBC(JDBCpoolJNDI.getDataSource());
    }

    public BdCreatorJDBC createDB() {
        return new BdCreatorJDBC(JDBCpoolJNDI.getDataSource());
    }
/*
    public PersonDAO createPersonDao() {
        return new PersonDaoJDBC(JDBCpool.getDataSource());
    }

    public PlanDAO createPlanDao() {
        return new PlanDaoJDBC(JDBCpool.getDataSource());
    }

    public AccDAO createAccDao() {
        return new AccDaoJDBC(JDBCpool.getDataSource());
    }

    public AccInfoDAO createAccInfoDao() {
        return new AccInfoDaoJDBC(JDBCpool.getDataSource());
    }

    public BdCreatorJDBC createDB() {
        return new BdCreatorJDBC(JDBCpool.getDataSource());
    }
*/


}
