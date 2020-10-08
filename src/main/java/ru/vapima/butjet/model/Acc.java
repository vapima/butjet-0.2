package ru.vapima.butjet.model;


import java.time.LocalDateTime;


public class Acc {
    private Integer id;
    private String name;
    private Integer balance;
    private LocalDateTime changTime;
    private Boolean active;
    private Integer personId;

  public Acc(Integer id, String name, Integer balance, LocalDateTime changTime, Boolean active, Integer personId) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.changTime = changTime;
        this.active = active;
        this.personId = personId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public LocalDateTime getChangTime() {
        return changTime;
    }

    public void setChangTime(LocalDateTime changTime) {
        this.changTime = changTime;
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
                ", changTime=" + changTime +
                ", isActive=" + active +
                ", personId=" + personId +
                '}';
    }
}
