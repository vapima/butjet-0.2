package ru.vapima.butjet.service;

import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.model.Plan;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class MyRdnMonth {

    Person person;
    ArrayList<Acc> accs;
    ArrayList<Plan> plans;

    public MyRdnMonth(Person person, ArrayList<Acc> accs, ArrayList<Plan> plans) {
        this.person = person;
        this.accs = accs;
        this.plans = plans;
    }

    public Integer getRdn() {
        return (totalAccActive() + totalPlan()) / daysLeftMonth();
    }

    private Integer totalAcc() {
        Integer total = 0;
        for (Acc a : accs) {
            total += a.getBalance();
        }
        return total;
    }

    private Integer totalAccActive() {
        Integer total = 0;
        for (Acc a : accs) {
            if (a.getActive()) {
                total += a.getBalance();
            }
        }
        return total;
    }

    private Integer totalPlan() {
        Integer totalPlans = 0;
        for (Plan p : plans) {
            totalPlans += p.getBalance();
        }
        return totalPlans;
    }

    private Integer daysLeftMonth() {
        return Period.between(LocalDate.now(), LocalDate.now().plusMonths(1).withDayOfMonth(1)).getDays();
    }

    @Override
    public String toString() {
        return getRdn().toString();
    }
}
