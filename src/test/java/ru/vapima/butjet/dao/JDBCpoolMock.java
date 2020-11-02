package ru.vapima.butjet.dao;

import org.apache.tomcat.jdbc.pool.DataSource;
//import javax.sql.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;


import java.sql.Connection;
import java.sql.SQLException;

public class JDBCpoolMock {
    private static DataSource dataSource;

    public static synchronized DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = getDataSourceReal();
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = getDataSource().getConnection();
        return connection;

    }

    private static DataSource getDataSourceReal() {
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:mysql://127.0.0.1:8889/butjet?serverTimezone=Europe/Moscow");
        p.setDriverClassName("com.mysql.jdbc.Driver");
        p.setUsername("butjet");
        p.setPassword("12345678");
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(100);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(5);
        p.setMaxIdle(30);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        DataSource datasource = new DataSource();
        datasource.setPoolProperties(p);
        return datasource;
    }


}
