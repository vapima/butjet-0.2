package ru.vapima.butjet.service.json;

import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.AccInfoEntity;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.model.Plan;

import java.util.ArrayList;


public interface Serial {
    String go(Person person);

    String go(Acc acc);

    String go(Plan plan);

    String go(AccInfoEntity accInfoEntity);

    String goAccs(ArrayList<Acc> accs);

    String goPlans(ArrayList<Plan> plans);

     String goAccInfoEntity(ArrayList<AccInfoEntity> accInfoEntitys);
}
