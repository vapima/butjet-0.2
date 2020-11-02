package ru.vapima.butjet.service.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.model.Plan;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;


public class GsonDeserial implements Deserial {

    GsonBuilder gsonBuilder;
    StringBuilder bodyData;

    public GsonDeserial(GsonBuilder gsonBuilder) {
        this.gsonBuilder = gsonBuilder;
    }

    public String getBody(HttpServletRequest req) throws IOException, PersonExeption {
        if (req == null) {
            throw new PersonExeption("Request is null");
        }
        if (bodyData==null) {
            BufferedReader reader = req.getReader();
            int intValueOfChar;
            bodyData = new StringBuilder();
            while ((intValueOfChar = reader.read()) != -1) {
                bodyData.append((char) intValueOfChar);
            }
        }
        return bodyData.toString();
    }


    @Override
    public Person getPerson(HttpServletRequest req) throws IOException, PersonExeption {
        Gson gson = gsonBuilder.create();
        return gson.fromJson(getBody(req), Person.class);
    }

    @Override
    public Acc getAccaunt(HttpServletRequest req) throws IOException, PersonExeption {
        Gson gson = gsonBuilder.create();
        return gson.fromJson(getBody(req), Acc.class);
    }

    @Override
    public Plan getPlan(HttpServletRequest req) throws IOException, PersonExeption {
        Gson gson = gsonBuilder.create();
        return gson.fromJson(getBody(req), Plan.class);
    }



}
