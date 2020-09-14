package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO<T> {
    T takeById(Integer id) throws SQLException;

    T takeByName(String name) throws SQLException;

    Integer delete(T t) throws SQLException;

    Integer update(T t) throws SQLException;

    Integer insert(T t) throws SQLException;

    ArrayList<T> takeAll() throws SQLException;
}

