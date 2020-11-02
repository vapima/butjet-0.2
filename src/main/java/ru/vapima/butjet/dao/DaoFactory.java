package ru.vapima.butjet.dao;


import ru.vapima.butjet.dao.impl.*;
import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.AccRec;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.model.Plan;

public class DaoFactory {

    public Dao<Person> createPersonDao() {
        return new PersonDaoJDBC(JDBCpoolJNDI.getDataSource());
    }
    public Dao<Plan> createPlanDao() {
        return new PlanDaoJDBC(JDBCpoolJNDI.getDataSource());
    }
    public Dao<Acc> createAccDao() {
        return new AccDaoJDBC(JDBCpoolJNDI.getDataSource());
    }
    public Dao<AccRec> createAccRecDao() {
        return new AccRecDaoJDBC(JDBCpoolJNDI.getDataSource());
    }
    public BdCreatorJDBC createDB() {
        return new BdCreatorJDBC(JDBCpoolJNDI.getDataSource());
    }

}
