package ru.vapima.butjet.dao;

import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Plan;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlanDAO extends DAO<Plan> {
    ArrayList<Plan> takeAllByForeignKey(Integer foreignKey) throws SQLException, AccExeption, PlanExeption;
}

