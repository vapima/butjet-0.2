package dao.Impl;

import dao.AccDAO;
import model.Acc;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class AccDaoJDBC implements AccDAO {
    DataSource dataSource;

    public AccDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Acc takeById(Integer id) throws SQLException {
        Acc acc = null;
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("select * from accs where id=?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                acc = new Acc(id, rs.getString("name"), rs.getInt("balance"), rs.getTimestamp("datetime").toLocalDateTime(), rs.getBoolean("active"), rs.getInt("personid"));
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
        return acc;
    }

    @Override
    public Acc takeByName(String name) throws SQLException {
        Acc acc = null;
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("select * from accs where name=?");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                acc = new Acc(rs.getInt("id"), name, rs.getInt("balance"), rs.getTimestamp("datetime").toLocalDateTime(), rs.getBoolean("active"), rs.getInt("personid"));
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
        return acc;
    }


    @Override
    public Integer delete(Acc acc) throws SQLException {
        int i;
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("DELETE FROM Accs WHERE id=?");
            statement.setInt(1, acc.getId());
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
    public Integer update(Acc acc) throws SQLException {
        int i;
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("UPDATE accs SET name=?,balance=?,datetime=?,active=? WHERE id=?");
            statement.setString(1, acc.getName());
            statement.setInt(2, acc.getBalance());
            statement.setTimestamp(3, Timestamp.valueOf(acc.getChangTime()));
            statement.setBoolean(4, acc.getActive());
            statement.setInt(5, acc.getId());
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
    public Integer insert(Acc acc) throws SQLException {
        int generatedId = 0;
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("INSERT INTO accs (name,balance,datetime,active,personid) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, acc.getName());
            statement.setInt(2, acc.getBalance());
            statement.setTimestamp(3, Timestamp.valueOf(acc.getChangTime()));
            statement.setBoolean(4, acc.getActive());
            statement.setInt(5, acc.getPersonId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedId = resultSet.getInt(1);

            }
            resultSet.close();
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
    public ArrayList<Acc> takeAll() throws SQLException {
        ArrayList<Acc> accs = new ArrayList<>();
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("select * from accs");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                accs.add(new Acc(rs.getInt("id"), rs.getString("name"), rs.getInt("balance"), rs.getTimestamp("datetime").toLocalDateTime(), rs.getBoolean("active"), rs.getInt("personid")));
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
        return accs;
    }


    @Override
    public ArrayList<Acc> takeAllByForeignKey(Integer foreignKey) throws SQLException {
        ArrayList<Acc> accs = new ArrayList<>();
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("select * from accs WHERE personid=?");
            statement.setInt(1, foreignKey);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                accs.add(new Acc(rs.getInt("id"), rs.getString("name"), rs.getInt("balance"), rs.getTimestamp("datetime").toLocalDateTime(), rs.getBoolean("active"), rs.getInt("personid")));
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
        return accs;
    }

    @Override
    public ArrayList<Acc> takeLastByForeignKey(Integer foreignKey) throws SQLException {
        ArrayList<Acc> accs = new ArrayList<>();
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM accs WHERE id IN(SELECT MAX(id) FROM accs GROUP BY name) and personid=?");
            statement.setInt(1, foreignKey);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                accs.add(new Acc(rs.getInt("id"), rs.getString("name"), rs.getInt("balance"), rs.getTimestamp("datetime").toLocalDateTime(), rs.getBoolean("active"), rs.getInt("personid")));
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
        return accs;
    }


}
