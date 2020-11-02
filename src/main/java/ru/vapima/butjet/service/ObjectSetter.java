package ru.vapima.butjet.service;

import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.AccRec;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.model.Plan;

public class ObjectSetter {
    public static void setPersonId(Object object, Person person){
        if (object instanceof Acc) {Acc acc= (Acc) object; acc.setPersonId(person.getId());}
        if (object instanceof AccRec) {AccRec accRec= (AccRec) object; accRec.setPersonId(person.getId());}
        if (object instanceof Plan){Plan plan=(Plan) object; plan.setIdPerson(person.getId());}
    }
}
