package ru.vapima.butjet.service.json;

import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.model.Plan;

import java.util.ArrayList;


public interface Serial {
    String parse(Person person);

    String parse(Acc acc);

    String parse(Plan plan);

    String parceAccsArray(ArrayList<Acc> accs);

    String parcePlansArray(ArrayList<Plan> plans);

}
