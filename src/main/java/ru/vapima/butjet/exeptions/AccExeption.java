package ru.vapima.butjet.exeptions;

public class AccExeption extends Exception {
    public AccExeption(String message) {
        super(message);
        System.out.println(this.toString());
    }

    public AccExeption(String message, Exception e) {
        super(message, e);
        System.out.println(this.toString());
    }
}
