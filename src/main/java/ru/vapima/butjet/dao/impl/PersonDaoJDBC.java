package ru.vapima.butjet.dao.impl;

import ru.vapima.butjet.dao.Dao;
import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Person;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class PersonDaoJDBC implements Dao<Person> {
    DataSource dataSource;
    public static final String SQL_TAKE_BY_ID ="SELECT * FROM persons WHERE id=?";
    public static final String SQL_TAKE_BY_NAME ="SELECT * FROM persons WHERE name=?";
    public static final String SQL_DELETE ="DELETE FROM Persons WHERE id=?";
    public static final String SQL_UPDATE ="UPDATE Persons SET name=?,token=?,password=? WHERE id=?";
    public static final String SQL_INSERT ="INSERT INTO Persons (name,token,password) VALUES (?,?,?)";
    public static final String SQL_TAKE_ALL ="SELECT * FROM persons";
    public static final String SQL_ID="id";
    public static final String SQL_NAME="name";
    public static final String SQL_PASSWORD="password";
    public static final String SQL_TOKEN="token";

    public PersonDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Person takeBy(Integer id) throws PersonExeption, SQLException {
        Person person = null;
        try (Connection conn=dataSource.getConnection();PreparedStatement statement = conn.prepareStatement(SQL_TAKE_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    person = new Person(id, rs.getString(SQL_NAME), rs.getString(SQL_TOKEN), rs.getString(SQL_PASSWORD));
                }
            }
        }
        if (person == null) {
            throw new PersonExeption("Person not found.");
        }
        return person;
    }

    @Override
    public Person takeBy(String name) throws PersonExeption, SQLException {
        Person person = null;
        try (Connection conn=dataSource.getConnection();PreparedStatement statement = conn.prepareStatement(SQL_TAKE_BY_NAME)) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    person = new Person(Integer.parseInt(rs.getString(SQL_ID)), name, rs.getString(SQL_TOKEN), rs.getString(SQL_PASSWORD));
                }
            }
        }
        if (person == null) {
            throw new PersonExeption("Person not found.");
        }
        return person;
    }

    @Override
    public Integer delete(Person person) throws SQLException {
        int i;
        try (Connection conn=dataSource.getConnection();PreparedStatement statement = conn.prepareStatement(SQL_DELETE)) {
            statement.setInt(1, person.getId());
            statement.execute("SET FOREIGN_KEY_CHECKS=0");
            i = statement.executeUpdate();
            statement.execute("SET FOREIGN_KEY_CHECKS=1");
        }
        return i;
    }

    @Override
    public Integer update(Person person) throws SQLException {
        int i;
        try (Connection conn=dataSource.getConnection();PreparedStatement statement = conn.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, person.getName());
            statement.setString(2, person.getToken());
            statement.setString(3, person.getPassword());
            statement.setInt(4, person.getId());
            i = statement.executeUpdate();
        }
        return i;
    }

    @Override
    public Integer insert(Person person) throws SQLException {
        int generatedId = 0;
        try (Connection conn=dataSource.getConnection();PreparedStatement statement = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, person.getName());
            statement.setString(2, person.getToken());
            statement.setString(3, person.getPassword());
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
    public ArrayList<Person> takeAll() throws PersonExeption, SQLException {
        ArrayList<Person> persons = new ArrayList<>();
        try (Connection conn=dataSource.getConnection();PreparedStatement statement = conn.prepareStatement(SQL_TAKE_ALL)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    persons.add(new Person(Integer.parseInt(rs.getString(SQL_ID)), rs.getString(SQL_NAME), rs.getString(SQL_TOKEN), rs.getString(SQL_PASSWORD)));
                }
            }
        }
        return persons;
    }

    @Override
    public ArrayList<Person> takeAllByForeignKey(Integer foreignKey) throws SQLException, AccExeption, PlanExeption, PersonExeption {
        throw new PersonExeption("Person does not have Foreign Key. This action is not available.");
    }


}
