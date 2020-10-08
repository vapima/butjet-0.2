package ru.vapima.butjet.exeptions;

public class ValidExeption extends PersonExeption {
    public ValidExeption(String message) {
        super(message);
    }

    public ValidExeption(String message, Exception e) {
        super(message, e);
    }
}
