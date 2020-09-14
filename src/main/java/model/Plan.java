package model;

import java.time.LocalDate;

public class Plan {
    private Integer id;
    private String name;
    private Integer balance;
    private LocalDate dateExpiration;
    private Integer personId;


    public Plan(Integer id, String name, Integer balance, LocalDate dateExpiration, Integer personId) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.dateExpiration = dateExpiration;
        this.personId = personId;
    }

    public Integer getIdPerson() {
        return personId;
    }

    public void setIdPerson(Integer idPerson) {
        this.personId = idPerson;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", dateExpiration=" + dateExpiration +
                '}';
    }
}
