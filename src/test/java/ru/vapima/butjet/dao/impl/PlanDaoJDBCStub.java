package ru.vapima.butjet.dao.impl;

import ru.vapima.butjet.dao.PlanDAO;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Plan;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PlanDaoJDBCStub implements PlanDAO {
Plan plan=new Plan(1,"test",0, LocalDate.parse("2029-01-01"),1);

    @Override
    public ArrayList<Plan> takeAllByForeignKey(Integer foreignKey) throws SQLException, AccExeption, PlanExeption {
      ArrayList<Plan>plans=new ArrayList<>();
      plans.add(plan);
              return plans;
    }

    @Override
    public Plan takeBy(Integer id) throws SQLException, PersonExeption, AccExeption, PlanExeption {
        return plan;
    }

    @Override
    public Plan takeBy(String name) throws SQLException, PersonExeption, AccExeption, PlanExeption {
        return plan;
    }

    @Override
    public Integer delete(Plan plan) throws SQLException {
        return 1;
    }

    @Override
    public Integer update(Plan plan) throws SQLException {
        return 1;
    }

    @Override
    public Integer insert(Plan plan) throws SQLException {
        return 1;
    }

    @Override
    public ArrayList<Plan> takeAll() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        ArrayList<Plan>plans=new ArrayList<>();
        plans.add(plan);
        return plans;
    }

    @Override
    public ArrayList<Plan> takeAll(String name) throws SQLException, PersonExeption, AccExeption, PlanExeption {
        ArrayList<Plan>plans=new ArrayList<>();
        plans.add(plan);
        return plans;
    }
}