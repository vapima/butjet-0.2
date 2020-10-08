package ru.vapima.butjet.dao.impl;

import ru.vapima.butjet.dao.AccInfoDAO;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.AccInfoEntity;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class AccInfoDaoJDBC implements AccInfoDAO {
    DataSource dataSource;

    public AccInfoDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public ArrayList<AccInfoEntity> takeAllByForeignKey(Integer foreignKey) throws SQLException {
        ArrayList<AccInfoEntity> accInfoEntities = new ArrayList<>();
            try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("select * from accsinfo WHERE personid=?")) {
                statement.setInt(1, foreignKey);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        accInfoEntities.add(new AccInfoEntity(rs.getInt("id"), rs.getInt("personid"), rs.getString("nameacc"), rs.getString("name"), rs.getString("description"), rs.getBoolean("active")));
                    }
                }
            }
        return accInfoEntities;
    }

    @Override
    public AccInfoEntity takeBy(Integer id) throws SQLException, AccExeption {
        AccInfoEntity accInfoEntity = null;
            try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("select * from accsinfo where id=?")) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        accInfoEntity = new AccInfoEntity(rs.getInt("id"), rs.getInt("personid"), rs.getString("nameacc"), rs.getString("name"), rs.getString("description"), rs.getBoolean("active"));
                    }
                }
            }
        if (accInfoEntity == null) {
            throw new AccExeption("Account not found.");
        }
        return accInfoEntity;
    }

    @Override
    public AccInfoEntity takeBy(String name) throws SQLException, AccExeption {
        AccInfoEntity accInfoEntity = null;
            try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("select * from accsinfo where nameacc=?")) {
                statement.setString(1, name);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        accInfoEntity = new AccInfoEntity(rs.getInt("id"), rs.getInt("personid"), rs.getString("nameacc"), rs.getString("name"), rs.getString("description"), rs.getBoolean("active"));
                    }
                }
            }
        if (accInfoEntity == null) {
            throw new AccExeption("Account not found.");
        }
        return accInfoEntity;
    }

    @Override
    public Integer delete(AccInfoEntity accInfoEntity) throws SQLException {
        int i;
            try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("DELETE FROM accsinfo WHERE id=?")) {
                statement.setInt(1, accInfoEntity.getId());
                i = statement.executeUpdate();
            }
        return i;
    }

    @Override
    public Integer update(AccInfoEntity accInfoEntity) throws SQLException {
        int i;
            try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("UPDATE accsinfo SET name=?,description=?,active=? WHERE id=?")) {
                statement.setString(1, accInfoEntity.getNameInfo());
                statement.setString(2, accInfoEntity.getDescription());
                statement.setBoolean(3, accInfoEntity.getActive());
                statement.setInt(4, accInfoEntity.getId());
                i = statement.executeUpdate();
            }
        return i;
    }

    @Override
    public Integer insert(AccInfoEntity accInfoEntity) throws SQLException {
        int generatedId = 0;
            try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("INSERT INTO accsinfo (personid,nameacc,name,description,active) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, accInfoEntity.getPersonId());
                statement.setString(2, accInfoEntity.getName());
                statement.setString(3, accInfoEntity.getNameInfo());
                statement.setString(4, accInfoEntity.getDescription());
                statement.setBoolean(5, accInfoEntity.getActive());
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
    public ArrayList<AccInfoEntity> takeAll() throws SQLException, PersonExeption, AccExeption, PlanExeption {
        ArrayList<AccInfoEntity> accInfoEntities = new ArrayList<>();
            try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("select * from accsinfo")) {
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        accInfoEntities.add(new AccInfoEntity(rs.getInt("id"), rs.getInt("personid"), rs.getString("nameacc"), rs.getString("name"), rs.getString("description"), rs.getBoolean("active")));
                    }
                }
            }
        return accInfoEntities;
    }

    @Override
    public ArrayList<AccInfoEntity> takeAll(String name) throws SQLException, PersonExeption, AccExeption, PlanExeption {
        ArrayList<AccInfoEntity> accInfoEntities = new ArrayList<>();
            try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("select * from accsinfo WHERE nameacc=?")) {
                statement.setString(1, name);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        accInfoEntities.add(new AccInfoEntity(rs.getInt("id"), rs.getInt("personid"), rs.getString("nameacc"), rs.getString("name"), rs.getString("description"), rs.getBoolean("active")));
                    }
                }
            }
        return accInfoEntities;
    }
}
