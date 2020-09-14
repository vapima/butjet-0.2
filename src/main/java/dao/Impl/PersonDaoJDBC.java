package dao.Impl;

import dao.PersonDAO;
import model.Person;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class PersonDaoJDBC implements PersonDAO {
    DataSource dataSource;

    public PersonDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Person takeById(Integer id) throws SQLException {
        Person person = null;
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("select * from persons where id=?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                person = new Person(id, rs.getString("name"), rs.getString("token"), rs.getString("password"));
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
        return person;
    }

    @Override
    public Person takeByName(String name) throws SQLException {
        Person person = null;
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("select * from persons where name=?");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                person = new Person(Integer.parseInt(rs.getString("id")), name, rs.getString("token"), rs.getString("password"));
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
        return person;
    }

    @Override
    public Integer delete(Person person) throws SQLException {
        int i;
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("DELETE FROM Persons WHERE id=?");
            statement.setInt(1, person.getId());
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
    public Integer update(Person person) throws SQLException {
        int i;
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("UPDATE Persons SET name=?,token=?,password=? WHERE id=?");
            statement.setString(1, person.getName());
            statement.setString(2, person.getToken());
            statement.setString(3, person.getPassword());
            statement.setInt(4, person.getId());
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
    public Integer insert(Person person) throws SQLException {
        int generatedId = 0;
        Connection con = dataSource.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("INSERT INTO Persons (name,token,password) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, person.getName());
            statement.setString(2, person.getToken());
            statement.setString(3, person.getPassword());
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
    public ArrayList<Person> takeAll() throws SQLException {
        Connection con = dataSource.getConnection();
        ArrayList<Person> persons = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement("select * from persons");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                persons.add(new Person(Integer.parseInt(rs.getString("id")), rs.getString("name"), rs.getString("token"), rs.getString("password")));
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
        return persons;
    }


}
