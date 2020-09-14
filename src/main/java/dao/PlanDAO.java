package dao;

import model.Plan;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlanDAO extends DAO<Plan> {
    ArrayList<Plan> takeAllByForeignKey(Integer foreignKey) throws SQLException;
}

