package ru.vapima.butjet.dao.impl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class BdCreatorJDBC {
    DataSource dataSource;

    public BdCreatorJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Integer creat(String sql) throws SQLException {
        Connection con = dataSource.getConnection();
        int i = 0;
            try (Statement statement = con.createStatement()) {
                i = statement.executeUpdate(sql);
            }
        return i;
    }

    public Integer creatPersons() throws SQLException {
        String sql = "CREATE TABLE persons " +
                "(id INTEGER(200) NOT NULL AUTO_INCREMENT, " +
                " name VARCHAR(100) not NULL, " +
                " token VARCHAR (100), " +
                " password VARCHAR (100) not NULL, " +
                " PRIMARY KEY (id))";
        return creat(sql);
    }

    public Integer creatPlans() throws SQLException {
        String sql = "CREATE TABLE plans " +
                "(id INTEGER(200) NOT NULL AUTO_INCREMENT, " +
                " personId INTEGER (100) not NULL, " +
                " name VARCHAR(100) not NULL, " +
                " balance INTEGER (100) not NULL, " +
                " date DATE NOT NULL, " +
                " PRIMARY KEY (id)," +
                " FOREIGN KEY (personId) REFERENCES persons(id))";
        return creat(sql);
    }

    public Integer creatAccs() throws SQLException {
        String sql = "CREATE TABLE accs " +
                "(id INTEGER(200) NOT NULL AUTO_INCREMENT, " +
                " personId INTEGER (100) not NULL, " +
                " name VARCHAR(100) not NULL, " +
                " balance INTEGER (100), " +
                " datetime TIMESTAMP NOT NULL , " +
                " active BOOLEAN, " +
                " PRIMARY KEY (id)," +
                " FOREIGN KEY (personId) REFERENCES persons(id))";
        return creat(sql);
    }
    public Integer creatAccRecs() throws SQLException {
        String sql = "CREATE TABLE accrecs " +
                "(id INTEGER(200) NOT NULL AUTO_INCREMENT, " +
                " balance INTEGER (100), " +
                " personId INTEGER (100) not NULL, " +
                " accId INTEGER (100) not NULL, " +
                " datetime TIMESTAMP NOT NULL , " +
                " PRIMARY KEY (id)," +
                " FOREIGN KEY (personId) REFERENCES persons(id), "+
                " FOREIGN KEY (accId) REFERENCES accs(id))";
        return creat(sql);
    }

}
