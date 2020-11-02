package ru.vapima.butjet.dao.impl;

import ru.vapima.butjet.dao.Dao;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.AccRec;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class AccRecDaoJDBC implements Dao<AccRec> {
    DataSource dataSource;
    public AccRecDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public AccRec takeBy(Integer id) throws SQLException, PersonExeption, AccExeption, PlanExeption {
        AccRec accRec = null;
        try (Connection con = dataSource.getConnection(); PreparedStatement statement = con.prepareStatement("select * from accrecs where id=?")) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    accRec=new AccRec(id,rs.getInt("balance"),rs.getInt("personid"),rs.getInt("accid"),rs.getTimestamp("datetime").toLocalDateTime());
                }
            }
        }
        if (accRec == null) {
            throw new AccExeption("Account Record not found.");
        }
        return accRec;
    }

    @Override
    public AccRec takeBy(String name) throws SQLException, PersonExeption, AccExeption, PlanExeption {
        throw new AccExeption("Not available. AccRec does not have name.");
    }

    @Override
    public Integer delete(AccRec accRec) throws SQLException, PersonExeption {
        int i;
        try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("DELETE FROM accrecs WHERE id=?")) {
            statement.setInt(1, accRec.getId());
            statement.execute("SET FOREIGN_KEY_CHECKS=0");
            i = statement.executeUpdate();
            statement.execute("SET FOREIGN_KEY_CHECKS=1");
        }
        return i;
    }

    @Override
    public Integer update(AccRec accRec) throws SQLException, PersonExeption {
        int i;
        try (Connection con = dataSource.getConnection(); PreparedStatement statement = con.prepareStatement("UPDATE accrecs SET balance=?,personId=?,accId=?,datetime=? WHERE id=?")) {
            statement.setInt(1,accRec.getBalance());
            statement.setInt(2,accRec.getPersonId());
            statement.setInt(3,accRec.getAccId());
            statement.setTimestamp(4, Timestamp.valueOf(accRec.getChangeTime()));
            statement.setInt(5,accRec.getId());
            i = statement.executeUpdate();
        }
        return i;
    }

    @Override
    public Integer insert(AccRec accRec) throws SQLException, PersonExeption {
        int generatedId = 0;
        try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("INSERT INTO accrecs (balance,personid,accid,datetime) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,accRec.getBalance());
            statement.setInt(2,accRec.getPersonId());
            statement.setInt(3,accRec.getAccId());
            statement.setTimestamp(4, Timestamp.valueOf(accRec.getChangeTime()));
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    generatedId = resultSet.getInt(1);
                }
            }
        }
        return generatedId;
    }

    @Override
    public ArrayList<AccRec> takeAll() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        ArrayList<AccRec> accRecs=new ArrayList<>();
        try (Connection con = dataSource.getConnection(); PreparedStatement statement = con.prepareStatement("select * from accrecs")) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    accRecs.add(new AccRec(rs.getInt("id"), rs.getInt("balance"), rs.getInt("personid"), rs.getInt("accid"), rs.getTimestamp("datetime").toLocalDateTime()));
                }
            }
        }
        return accRecs;
    }

    @Override
    public ArrayList<AccRec> takeAllByForeignKey(Integer foreignKey) throws SQLException, AccExeption, PlanExeption, PersonExeption {
        ArrayList<AccRec> accRecs=new ArrayList<>();
        try (Connection con = dataSource.getConnection(); PreparedStatement statement = con.prepareStatement("select * from accrecs WHERE personid=?")) {
            statement.setInt(1, foreignKey);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    accRecs.add(new AccRec(rs.getInt("id"), rs.getInt("balance"), rs.getInt("personid"), rs.getInt("accid"), rs.getTimestamp("datetime").toLocalDateTime()));
                }
            }
        }
        return accRecs;
    }
}
