package ru.vapima.butjet.exeptions;

public class PlanExeption extends Exception {
    public PlanExeption(String message) {
        super(message);
    }

    public PlanExeption(String message, Exception e) {
        super(message, e);
    }
}
