package ru.vapima.butjet.dao.impl;

import ru.vapima.butjet.dao.AccDAO;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Acc;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AccDaoJDBCStub implements AccDAO {

Acc acc=new Acc(1,"test",0, LocalDateTime.parse("2029-10-01T16:11:34.200842"),true,1);
    @Override
    public ArrayList<Acc> takeAllByForeignKey(Integer foreignKey) throws SQLException, AccExeption {
        ArrayList<Acc> accs=new ArrayList<>();
        accs.add(acc);
        return accs;
    }

    @Override
    public ArrayList<Acc> takeLastByForeignKey(Integer foreignKey) throws SQLException, AccExeption {
        ArrayList<Acc> accs=new ArrayList<>();
        accs.add(acc);
        return accs;
    }

    @Override
    public Integer deleteAllByNameAndIdPerson(String name, Integer id) throws SQLException {
        return 1;
    }

    @Override
    public Acc takeBy(Integer id) throws SQLException, PersonExeption, AccExeption, PlanExeption {
        return acc;
    }

    @Override
    public Acc takeBy(String name) throws SQLException, PersonExeption, AccExeption, PlanExeption {
        return acc;
    }

    @Override
    public Integer delete(Acc acc) throws SQLException {
        return 1;
    }

    @Override
    public Integer update(Acc acc) throws SQLException {
        return 1;
    }

    @Override
    public Integer insert(Acc acc) throws SQLException {
        return 1;
    }

    @Override
    public ArrayList<Acc> takeAll() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        ArrayList<Acc> accs=new ArrayList<>();
        accs.add(acc);
        return accs;
    }

    @Override
    public ArrayList<Acc> takeAll(String name) throws SQLException, PersonExeption, AccExeption, PlanExeption {
        ArrayList<Acc> accs=new ArrayList<>();
        accs.add(acc);
        return accs;
    }
}