package service;

import model.Person;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyRdnMonthTest {

    @Test
    public void getRdn() {
        MyRdnMonth myRdnMonth = new MyRdnMonth(new Person(0, "", "", ""));
        assertEquals(0, myRdnMonth.getRdn().intValue());
    }
}