package ru.vapima.butjet.dao;

import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.model.AccInfoEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AccInfoDAO extends DAO<AccInfoEntity> {
    ArrayList<AccInfoEntity> takeAllByForeignKey(Integer foreignKey) throws SQLException, AccExeption;

}

