package service;

import model.Acc;
import model.Person;
import model.Plan;

import java.time.LocalDate;
import java.time.Period;

public class MyRdnMonth {

    Person person;


    public MyRdnMonth(Person person) {
        this.person = person;
    }

    public Integer getRdn() {
        return (totalAcc() + totalPlan()) / daysLeftMonth();
    }

    private Integer totalAcc() {
        Integer total = 0;
        for (Acc a : person.getAccs()) {
            total += a.getBalance();
        }
        return total;
    }

    private Integer totalAccActive() {
        Integer total = 0;
        for (Acc a : person.getAccs()) {
            if (a.getActive()) {
                total += a.getBalance();
            }
        }
        return total;
    }

    private Integer totalPlan() {
        Integer totalPlans = 0;
        for (Plan p : person.getPlans()) {
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
