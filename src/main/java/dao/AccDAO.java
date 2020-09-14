package dao;

import model.Acc;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AccDAO extends DAO<Acc> {
    ArrayList<Acc> takeAllByForeignKey(Integer foreignKey) throws SQLException;

    ArrayList<Acc> takeLastByForeignKey(Integer foreignKey) throws SQLException;
}

