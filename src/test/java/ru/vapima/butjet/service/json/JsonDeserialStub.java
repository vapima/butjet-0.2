package ru.vapima.butjet.service.json;

import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.AccInfoEntity;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.model.Plan;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class JsonDeserialStub implements Deserial {

    @Override
    public Person getPerson(HttpServletRequest req) throws IOException, PersonExeption {
        return new Person(1,"test","test","test");
    }

    @Override
    public Acc getAccaunt(HttpServletRequest req) throws IOException, PersonExeption {
        return new Acc(1,"test",0, LocalDateTime.parse("2029-10-01T16:11:34.200842"),true,1);

    }

    @Override
    public Plan getPlan(HttpServletRequest req) throws IOException, PersonExeption {
        return new Plan(1,"test",0, LocalDate.parse("2029-01-01"),1);
    }

    @Override
    public AccInfoEntity getAccInfoEntity(HttpServletRequest req) throws IOException, PersonExeption {
        return new AccInfoEntity(1,1,"test","test","test",true);
    }

}