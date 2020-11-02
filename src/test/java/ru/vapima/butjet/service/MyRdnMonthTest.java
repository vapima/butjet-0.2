package ru.vapima.butjet.service;

import org.junit.Test;
import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.Plan;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MyRdnMonthTest {

    @Test
    public void getRdn() {
        ArrayList<Acc> accs=new ArrayList<>();
        ArrayList<Plan> plans=new ArrayList<>();
        accs.add(new Acc(null,null,100,null,true,null));
        accs.add(new Acc(null,null,100,null,true,null));
        accs.add(new Acc(null,null,100,null,true,null));
        accs.add(new Acc(null,null,-99999,null,false,null));
        plans.add(new Plan(null,null,1000,null,null));
        plans.add(new Plan(null,null,-500,null,null));
        plans.add(new Plan(null,null,-700,null,null));
        MyRdnMonth myRdnMonth=new MyRdnMonth(accs,plans){
            @Override
            public Integer daysLeftMonth() {
                return 1;
            }
        };
        assertEquals(Integer.valueOf(100),myRdnMonth.getRdn());
    }
}