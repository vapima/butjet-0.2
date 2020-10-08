package ru.vapima.butjet.dao.impl;

import ru.vapima.butjet.dao.AccDAO;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.model.Acc;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class AccDaoJDBC implements AccDAO {
    DataSource dataSource;

    public AccDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Acc takeBy(Integer id) throws SQLException, AccExeption {
        Acc acc = null;
            try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("select * from accs where id=?")) {
                statement.setInt(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        acc = new Acc(id, rs.getString("name"), rs.getInt("balance"), rs.getTimestamp("datetime").toLocalDateTime(), rs.getBoolean("active"), rs.getInt("personid"));
                    }
                }
            }
        if (acc == null) {
            throw new AccExeption("Account not found.");
        }
        return acc;
    }

    @Override
    public Acc takeBy(String name) throws SQLException, AccExeption {
        Acc acc = null;
            try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("select * from accs where name=? ORDER BY ID DESC LIMIT 1")) {
                statement.setString(1, name);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        acc = new Acc(rs.getInt("id"), name, rs.getInt("balance"), rs.getTimestamp("datetime").toLocalDateTime(), rs.getBoolean("active"), rs.getInt("personid"));
                    }
                }
            }
        if (acc == null) {
            throw new AccExeption("Account not found.");
        }
        return acc;
    }


    @Override
    public Integer delete(Acc acc) throws SQLException {
        int i;
        Connection con = dataSource.getConnection();
            try (PreparedStatement statement = con.prepareStatement("DELETE FROM Accs WHERE id=?")) {
                statement.setInt(1, acc.getId());
                i = statement.executeUpdate();
            }
        return i;
    }

    @Override
    public Integer update(Acc acc) throws SQLException {
        int i;
            try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("UPDATE accs SET name=?,balance=?,datetime=?,active=? WHERE id=?")) {
                statement.setString(1, acc.getName());
                statement.setInt(2, acc.getBalance());
                statement.setTimestamp(3, Timestamp.valueOf(acc.getChangTime()));
                statement.setBoolean(4, acc.getActive());
                statement.setInt(5, acc.getId());
                i = statement.executeUpdate();
            }
        return i;
    }

    @Override
    public Integer insert(Acc acc) throws SQLException {
        int generatedId = 0;
            try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("INSERT INTO accs (name,balance,datetime,active,personid) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, acc.getName());
                statement.setInt(2, acc.getBalance());
                statement.setTimestamp(3, Timestamp.valueOf(acc.getChangTime()));
                statement.setBoolean(4, acc.getActive());
                statement.setInt(5, acc.getPersonId());
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
    public ArrayList<Acc> takeAll() throws SQLException, AccExeption {
        ArrayList<Acc> accs = new ArrayList<>();
            try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("select * from accs")) {
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        accs.add(new Acc(rs.getInt("id"), rs.getString("name"), rs.getInt("balance"), rs.getTimestamp("datetime").toLocalDateTime(), rs.getBoolean("active"), rs.getInt("personid")));
                    }
                }
            }
        return accs;
    }

    @Override
    public ArrayList<Acc> takeAll(String name) throws SQLException, AccExeption {
        ArrayList<Acc> accs = new ArrayList<>();
            try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("select * from accs where name=?")) {
                statement.setString(1, name);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        accs.add(new Acc(rs.getInt("id"), rs.getString("name"), rs.getInt("balance"), rs.getTimestamp("datetime").toLocalDateTime(), rs.getBoolean("active"), rs.getInt("personid")));
                    }
                }
            }
        return accs;
    }


    @Override
    public ArrayList<Acc> takeAllByForeignKey(Integer foreignKey) throws SQLException {
        ArrayList<Acc> accs = new ArrayList<>();
            try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("select * from accs WHERE personid=?")) {
                statement.setInt(1, foreignKey);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        accs.add(new Acc(rs.getInt("id"), rs.getString("name"), rs.getInt("balance"), rs.getTimestamp("datetime").toLocalDateTime(), rs.getBoolean("active"), rs.getInt("personid")));
                    }
                }
            }
        return accs;
    }

    @Override
    public ArrayList<Acc> takeLastByForeignKey(Integer foreignKey) throws SQLException {
        ArrayList<Acc> accs = new ArrayList<>();
            try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("SELECT * FROM accs WHERE id IN(SELECT MAX(id) FROM accs GROUP BY name) and personid=?")) {
                statement.setInt(1, foreignKey);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        accs.add(new Acc(rs.getInt("id"), rs.getString("name"), rs.getInt("balance"), rs.getTimestamp("datetime").toLocalDateTime(), rs.getBoolean("active"), rs.getInt("personid")));
                    }
                }
            }
        return accs;
    }

    @Override
    public Integer deleteAllByNameAndIdPerson(String name, Integer id) throws SQLException {
        int i;
            try (Connection con = dataSource.getConnection();PreparedStatement statement = con.prepareStatement("DELETE FROM Accs WHERE name=? and personid=?")) {
                statement.setString(1, name);
                statement.setInt(2, id);
                i = statement.executeUpdate();
            }
        return i;
    }


}
