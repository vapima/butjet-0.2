package ru.vapima.butjet.dao;

import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;
import ru.vapima.butjet.model.Plan;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Dao<T> {
    T takeBy(Integer id) throws SQLException, PersonExeption, AccExeption, PlanExeption;

    T takeBy(String name) throws SQLException, PersonExeption, AccExeption, PlanExeption;

    Integer delete(T t) throws SQLException, PersonExeption;

    Integer update(T t) throws SQLException, PersonExeption;

    Integer insert(T t) throws SQLException, PersonExeption;

    ArrayList<T> takeAll() throws SQLException, PersonExeption, AccExeption, PlanExeption;

    ArrayList<T> takeAllByForeignKey(Integer foreignKey) throws SQLException, AccExeption, PlanExeption, PersonExeption;
}

