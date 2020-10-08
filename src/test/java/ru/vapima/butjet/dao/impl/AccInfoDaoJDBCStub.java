package ru.vapima.butjet.dao.impl;

import ru.vapima.butjet.dao.AccInfoDAO;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.AccInfoEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class AccInfoDaoJDBCStub implements AccInfoDAO {
    AccInfoEntity accInfoEntity=new AccInfoEntity(1,1,"testIT","testIT","testIT",true);
    @Override
    public ArrayList<AccInfoEntity> takeAllByForeignKey(Integer foreignKey) throws SQLException, AccExeption {
        ArrayList<AccInfoEntity> accInfos=new ArrayList<>();
        accInfos.add(accInfoEntity);
        return accInfos;
    }

    @Override
    public AccInfoEntity takeBy(Integer id) throws SQLException, PersonExeption, AccExeption, PlanExeption {
        return accInfoEntity;

    }

    @Override
    public AccInfoEntity takeBy(String name) throws SQLException, PersonExeption, AccExeption, PlanExeption {
        return accInfoEntity;
    }

    @Override
    public Integer delete(AccInfoEntity accInfoEntity) throws SQLException {
        return 1;
    }

    @Override
    public Integer update(AccInfoEntity accInfoEntity) throws SQLException {
        return 1;
    }

    @Override
    public Integer insert(AccInfoEntity accInfoEntity) throws SQLException {
        return 1;
    }

    @Override
    public ArrayList<AccInfoEntity> takeAll() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        ArrayList<AccInfoEntity> accInfos=new ArrayList<>();
        accInfos.add(accInfoEntity);
        return accInfos;
    }

    @Override
    public ArrayList<AccInfoEntity> takeAll(String name) throws SQLException, PersonExeption, AccExeption, PlanExeption {
        ArrayList<AccInfoEntity> accInfos=new ArrayList<>();
        accInfos.add(accInfoEntity);
        return accInfos;
    }
}
