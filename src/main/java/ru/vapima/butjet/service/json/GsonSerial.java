package ru.vapima.butjet.service.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.model.Plan;

import java.util.ArrayList;

public class GsonSerial implements Serial {
    GsonBuilder gsonBuilder;

    public GsonSerial(GsonBuilder gsonBuilder) {
        this.gsonBuilder = gsonBuilder;
    }

    @Override
    public String parse(Person person) {
        Gson gson=gsonBuilder.create();
        return gson.toJson(person);
    }

    @Override
    public String parse(Acc acc) {
        Gson gson=gsonBuilder.create();
        return gson.toJson(acc);
    }

    @Override
    public String parse(Plan plan) {
        Gson gson=gsonBuilder.create();
        return gson.toJson(plan);
    }

    @Override
    public String parceAccsArray(ArrayList<Acc> accs) {
        Gson gson=gsonBuilder.create();
        return gson.toJson(accs);
    }

    @Override
    public String parcePlansArray(ArrayList<Plan> plans) {
        Gson gson=gsonBuilder.create();
        return gson.toJson(plans);
    }
}
