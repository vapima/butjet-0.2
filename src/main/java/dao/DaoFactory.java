package dao;


import dao.Impl.AccDaoJDBC;
import dao.Impl.BdCreatorJDBC;
import dao.Impl.PersonDaoJDBC;
import dao.Impl.PlanDaoJDBC;

public class DaoFactory {
    /*   public static PersonDAO createPersonDao() {
        return new PersonDaoJDBC(JDBCpool.getDataSource());
    }

    public static PlanDAO createPlanDao() {
        return new PlanDaoJDBC(JDBCpool.getDataSource());
    }

    public static AccDAO createAccDao() {
        return new AccDaoJDBC(JDBCpool.getDataSource());
    }

    public static BdCreatorJDBC createDB() {
        return new BdCreatorJDBC(JDBCpool.getDataSource());
    }*/
    //
    public static PersonDAO createPersonDao() {
        return new PersonDaoJDBC(JDBCpoolJNDI.getDataSource());
    }

    public static PlanDAO createPlanDao() {
        return new PlanDaoJDBC(JDBCpoolJNDI.getDataSource());
    }

    public static AccDAO createAccDao() {
        return new AccDaoJDBC(JDBCpoolJNDI.getDataSource());
    }

    public static BdCreatorJDBC createDB() {
        return new BdCreatorJDBC(JDBCpoolJNDI.getDataSource());
    }
}
