package ru.vapima.butjet.model;


import java.time.LocalDateTime;


public class Acc implements PersonID{
    private Integer id;
    private String name;
    private Integer balance;
    private LocalDateTime changeTime;
    private Boolean active;
    private Integer personId;

  public Acc(Integer id, String name, Integer balance, LocalDateTime changeTime, Boolean active, Integer personId) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.changeTime = changeTime;
        this.active = active;
        this.personId = personId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public LocalDateTime getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(LocalDateTime changeTime) {
        this.changeTime = changeTime;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        active = active;
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

    @Override
    public String toString() {
        return "Acc{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", changeTime=" + changeTime +
                ", isActive=" + active +
                ", personId=" + personId +
                '}';
    }

    @Override
    public Integer takePersonId() {
        return personId;
    }
}
