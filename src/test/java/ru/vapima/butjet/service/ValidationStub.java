package ru.vapima.butjet.service;

import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.model.Plan;

public class ValidationStub extends Validation{

    @Override
    public boolean check(Person person) throws PersonExeption {
        return true;
    }

    @Override
    public boolean check(Acc acc) {
        return  true;
    }

    @Override
    public boolean check(Plan plan) {
        return true;
    }

    @Override
    public String isTokenOK(String str) throws PersonExeption {
        return str;
    }
}