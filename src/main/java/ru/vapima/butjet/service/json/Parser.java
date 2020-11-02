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
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Parser<T> {
    private static GsonBuilder gsonBuilder;


    public Parser() {
        initGsonBuilder();
    }

    private static GsonBuilder initGsonBuilder() {
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        return gsonBuilder;
    }

    public String parse(T t) {
        Gson gson=gsonBuilder.create();
        return gson.toJson(t);
    }
    public String parceArray(ArrayList<T> ts) {
        Gson gson=gsonBuilder.create();
        return gson.toJson(ts);
    }

    public T deparse(String json, Class cls) throws IOException, PersonExeption {
        Gson gson = gsonBuilder.create();
        return (T) gson.fromJson(json, cls);
    }



}
