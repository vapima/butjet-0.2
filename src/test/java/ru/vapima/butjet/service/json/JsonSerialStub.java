package ru.vapima.butjet.service.json;

import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.AccInfoEntity;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.model.Plan;

import java.util.ArrayList;

public class JsonSerialStub implements Serial {

    @Override
    public String go(Person person) {
        return person.toString();
    }

    @Override
    public String go(Acc acc) {
        return acc.toString();
    }

    @Override
    public String go(Plan plan) {
        return plan.toString();
    }

    @Override
    public String go(AccInfoEntity accInfoEntity) {
       return accInfoEntity.toString();
    }

    @Override
    public String goAccs(ArrayList<Acc> accs) {
        return accs.toString();

    }

    @Override
    public String goPlans(ArrayList<Plan> plans) {
        return plans.toString();
    }

    @Override
    public String goAccInfoEntity(ArrayList<AccInfoEntity> accInfoEntitys) {
        return accInfoEntitys.toString();
    }
}