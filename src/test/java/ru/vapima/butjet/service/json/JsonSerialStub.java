package ru.vapima.butjet.service.json;

import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.model.Plan;

import java.util.ArrayList;

public class JsonSerialStub implements Serial {

    @Override
    public String parse(Person person) {
        return person.toString();
    }

    @Override
    public String parse(Acc acc) {
        return acc.toString();
    }

    @Override
    public String parse(Plan plan) {
        return plan.toString();
    }


    @Override
    public String parceAccsArray(ArrayList<Acc> accs) {
        return accs.toString();

    }

    @Override
    public String parcePlansArray(ArrayList<Plan> plans) {
        return plans.toString();
    }

}