package dao.Impl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BdCreatorJDBC {
    DataSource dataSource;

    public BdCreatorJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Integer creat(String sql) throws SQLException {
        Connection con = dataSource.getConnection();
        int i = 0;
        try {
            Statement statement = con.createStatement();
            i = statement.executeUpdate(sql);
            statement.close();
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
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

    public Integer creatAccsPerson(Integer id) throws SQLException {
        String sql = "CREATE TABLE accs" + id + " " +
                "(id INTEGER(200) NOT NULL AUTO_INCREMENT, " +
                " personId INTEGER (100) not NULL, " +
                " name VARCHAR(100) not NULL, " +
                " type VARCHAR(100) not NULL, " +
                " balance INTEGER (100), " +
                " changeTime VARCHAR(100), " +
                " active BOOLEAN, " +
                " PRIMARY KEY (id)," +
                " FOREIGN KEY (personId) REFERENCES persons(id))";
        return creat(sql);
    }

}
