package ru.vapima.butjet.dao;

import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.model.Acc;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AccDAO extends DAO<Acc> {
    ArrayList<Acc> takeAllByForeignKey(Integer foreignKey) throws SQLException, AccExeption;

    ArrayList<Acc> takeLastByForeignKey(Integer foreignKey) throws SQLException, AccExeption;

    Integer deleteAllByNameAndIdPerson(String name, Integer id) throws SQLException;
}

