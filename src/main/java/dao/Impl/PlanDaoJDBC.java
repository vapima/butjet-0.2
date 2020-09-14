package dao.Impl;

import dao.PlanDAO;
import model.Plan;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class PlanDaoJDBC implements PlanDAO {
    DataSource dataSource;

    public PlanDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Plan takeById(Integer id) throws SQLException {
        Plan plan = null;
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("select * from plans where id=?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                plan = new Plan(id, rs.getString("name"), rs.getInt("balance"), rs.getDate("date").toLocalDate(), rs.getInt("personId"));
            }
            rs.close();
            statement.close();
        } finally {
            if (con != null && dataSource != null) {
                try {
                    con.close();
                } catch (Exception ignore) {
                }
            }
        }
        return plan;
    }

    @Override
    public Plan takeByName(String name) throws SQLException {
        Plan plan = null;
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("select * from plans where name=?");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                plan = new Plan(rs.getInt("id"), name, rs.getInt("balance"), rs.getDate("date").toLocalDate(), rs.getInt("personId"));
            }
            rs.close();
            statement.close();
        } finally {
            if (con != null && dataSource != null) {
                try {
                    con.close();
                } catch (Exception ignore) {
                }
            }
        }
        return plan;
    }

    @Override
    public Integer delete(Plan plan) throws SQLException {
        int i;
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("DELETE FROM plans WHERE id=?");
            statement.setInt(1, plan.getId());
            i = statement.executeUpdate();
            statement.close();
        } finally {
            if (con != null && dataSource != null) {
                try {
                    con.close();
                } catch (Exception ignore) {
                }
            }
        }
        return i;
    }

    @Override
    public Integer update(Plan plan) throws SQLException {
        int i;
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("UPDATE plans SET name=?,balance=?,date=? WHERE id=?");
            statement.setString(1, plan.getName());
            statement.setInt(2, plan.getBalance());
            statement.setDate(3, Date.valueOf(plan.getDateExpiration()));
            statement.setInt(4, plan.getId());
            i = statement.executeUpdate();
            statement.close();
        } finally {
            if (con != null && dataSource != null) {
                try {
                    con.close();
                } catch (Exception ignore) {
                }
            }
        }
        return i;
    }

    @Override
    public Integer insert(Plan plan) throws SQLException {
        int generatedId = 0;
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("INSERT INTO plans (name,balance,date,personId) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, plan.getName());
            statement.setInt(2, plan.getBalance());
            statement.setDate(3, Date.valueOf(plan.getDateExpiration()));
            statement.setInt(4, plan.getIdPerson());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedId = resultSet.getInt(1);

            }
            statement.close();
        } finally {
            if (con != null && dataSource != null) {
                try {
                    con.close();
                } catch (Exception ignore) {
                }
            }
        }
        return generatedId;
    }

    @Override
    public ArrayList<Plan> takeAll() throws SQLException {
        ArrayList<Plan> plans = new ArrayList<>();
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("select * from plans");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                plans.add(new Plan(rs.getInt("id"), rs.getString("name"), rs.getInt("balance"), rs.getDate("date").toLocalDate(), rs.getInt("personid")));
            }
            rs.close();
            statement.close();
        } finally {
            if (con != null && dataSource != null) {
                try {
                    con.close();
                } catch (Exception ignore) {
                }
            }
        }
        return plans;
    }


    @Override
    public ArrayList<Plan> takeAllByForeignKey(Integer foreignKey) throws SQLException {
        ArrayList<Plan> plans = new ArrayList<>();
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("select * from plans WHERE personid=?");
            statement.setInt(1, foreignKey);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                plans.add(new Plan(rs.getInt("id"), rs.getString("name"), rs.getInt("balance"), rs.getDate("date").toLocalDate(), rs.getInt("personid")));
            }
            rs.close();
            statement.close();
        } finally {
            if (con != null && dataSource != null) {
                try {
                    con.close();
                } catch (Exception ignore) {
                }
            }
        }
        return plans;
    }
}
