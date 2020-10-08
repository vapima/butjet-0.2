package ru.vapima.butjet.answers;

import ru.vapima.butjet.exeptions.AccExeption;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.PlanExeption;

import java.io.IOException;
import java.sql.SQLException;

public interface Answer {
    String run() throws PersonExeption, AccExeption, SQLException, PlanExeption, IOException;
}
