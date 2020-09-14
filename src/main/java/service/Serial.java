package service;

import model.Acc;
import model.Person;
import model.Plan;


public interface Serial {
    String go(Person person);

    String go(Acc acc);

    String go(Plan plan);
}
