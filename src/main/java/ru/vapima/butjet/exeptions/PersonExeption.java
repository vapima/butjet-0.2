package ru.vapima.butjet.exeptions;

public class PersonExeption extends Exception {
    public PersonExeption(String message) {
        super(message);

        System.out.println(this.toString());
    }

    public PersonExeption(String message, Exception e) {
        super(message, e);
        System.out.println(this.toString());
    }

}
