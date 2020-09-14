package dao;

import org.apache.tomcat.jdbc.pool.DataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JDBCpoolJNDI {
    private static DataSource dataSource;

    public static synchronized DataSource getDataSource() {
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
    }


}
