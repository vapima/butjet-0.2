package ru.vapima.butjet.service.json;

import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.model.Plan;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface Deserial {


    Person getPerson(HttpServletRequest req) throws IOException, PersonExeption;

    Acc getAccaunt(HttpServletRequest req) throws IOException, PersonExeption;

    Plan getPlan(HttpServletRequest req) throws IOException, PersonExeption;

}
