package service;

import model.Acc;
import model.Person;
import model.Plan;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class JsonSerialTest {
    JsonSerial jsonSerial = new JsonSerial();

    @Test
    public void go() {
        assertEquals("{\"Plans\":[],\"name\":\"test\",\"id\":1,\"Accs\":[]}", jsonSerial.go(new Person(1, "test", "", "")));
    }

    @Test
    public void testGo() {
        assertEquals("{\"changeTime\":\"2020-09-14T16:27:17.533765\",\"balance\":100,\"name\":\"test\",\"active\":true,\"id\":1}", jsonSerial.go(new Acc(1, "test", 100, LocalDateTime.parse("2020-09-14T16:27:17.533765"), true, 1)));
    }

    @Test
    public void testGo1() {
        assertEquals("{\"balance\":200,\"dateExpiration\":\"2021-10-10\",\"name\":\"test\",\"id\":0}", jsonSerial.go(new Plan(0, "test", 200, LocalDate.parse("2021-10-10"), 1)));
    }
}