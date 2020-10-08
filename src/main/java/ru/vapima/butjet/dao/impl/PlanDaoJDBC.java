package ru.vapima.butjet.dao.impl;

import ru.vapima.butjet.dao.PlanDAO;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Plan;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class PlanDaoJDBC implements PlanDAO {
    DataSource dataSource;

    public PlanDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Plan takeBy(Integer id) throws SQLException, PlanExeption {
        Plan plan = null;
            try ( Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("select * from plans where id=?")) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        plan = new Plan(id, rs.getString("name"), rs.getInt("balance"), rs.getDate("date").toLocalDate(), rs.getInt("personId"));
                    }
                }
            }
        if (plan == null) {
            throw new PlanExeption("Plan not found.");
        }
        return plan;
    }

    @Override
    public Plan takeBy(String name) throws SQLException, PlanExeption {
        Plan plan = null;
        try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("select * from plans where name=?")) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    plan = new Plan(rs.getInt("id"), name, rs.getInt("balance"), rs.getDate("date").toLocalDate(), rs.getInt("personId"));
                }
            }
        }
        if (plan == null) {
            throw new PlanExeption("Plan not found.");
        }
        return plan;
    }

    @Override
    public Integer delete(Plan plan) throws SQLException {
        int i;
            try ( Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("DELETE FROM plans WHERE id=?")) {
                statement.setInt(1, plan.getId());
                i = statement.executeUpdate();
            }
        return i;
    }

    @Override
    public Integer update(Plan plan) throws SQLException {
        int i;
            try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("UPDATE plans SET name=?,balance=?,date=? WHERE id=?")) {
                statement.setString(1, plan.getName());
                statement.setInt(2, plan.getBalance());
                statement.setDate(3, Date.valueOf(plan.getDateExpiration()));
                statement.setInt(4, plan.getId());
                i = statement.executeUpdate();
            }
        return i;
    }

    @Override
    public Integer insert(Plan plan) throws SQLException {
        int generatedId = 0;
            try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("INSERT INTO plans (name,balance,date,personId) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, plan.getName());
                statement.setInt(2, plan.getBalance());
                statement.setDate(3, Date.valueOf(plan.getDateExpiration()));
                statement.setInt(4, plan.getIdPerson());
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    generatedId = resultSet.getInt(1);

                }
            }
        return generatedId;
    }

    @Override
    public ArrayList<Plan> takeAll() throws SQLException, PlanExeption {
        ArrayList<Plan> plans = new ArrayList<>();
            try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("select * from plans")) {
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        plans.add(new Plan(rs.getInt("id"), rs.getString("name"), rs.getInt("balance"), rs.getDate("date").toLocalDate(), rs.getInt("personid")));
                    }
                }
            }
        return plans;
    }

    @Override
    public ArrayList<Plan> takeAll(String name) throws SQLException, PlanExeption {
        ArrayList<Plan> plans = new ArrayList<>();
            try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("select * from plans where name=?")) {
                statement.setString(1, name);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        plans.add(new Plan(rs.getInt("id"), rs.getString("name"), rs.getInt("balance"), rs.getDate("date").toLocalDate(), rs.getInt("personid")));
                    }
                }
            }
        return plans;
    }


    @Override
    public ArrayList<Plan> takeAllByForeignKey(Integer foreignKey) throws SQLException{
        ArrayList<Plan> plans = new ArrayList<>();
            try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("select * from plans WHERE personid=?")) {
                statement.setInt(1, foreignKey);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        plans.add(new Plan(rs.getInt("id"), rs.getString("name"), rs.getInt("balance"), rs.getDate("date").toLocalDate(), rs.getInt("personid")));
                    }
                }
            }
        return plans;
    }
}
