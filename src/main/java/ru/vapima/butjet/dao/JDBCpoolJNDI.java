package ru.vapima.butjet.dao;

//import org.apache.tomcat.jdbc.pool.DataSource;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCpoolJNDI {
    private static DataSource dataSource;

    /*public static synchronized DataSource getDataSource() {
        try {
            if (dataSource == null) {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                dataSource = (DataSource) envContext.lookup("jdbc/butjet");
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }


        return dataSource;
    }*/
    public static DataSource getDataSource()  {
        try {
            InitialContext initContext=  new InitialContext();
            DataSource dataSource=(DataSource) initContext.lookup("java:comp/env/jdbc/butjet");
            if (dataSource==null){throw new SQLException("We dont have Data Source."); }
            try (Connection connection = dataSource.getConnection()) {
                if (connection == null) {
                    throw new SQLException("We can not take connection from data source.");
                }
            }
            return dataSource;
        } catch (NamingException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
