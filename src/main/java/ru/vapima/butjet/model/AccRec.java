package ru.vapima.butjet.model;

import java.time.LocalDateTime;

public class AccRec implements PersonID {
    private Integer id;
    private Integer balance;
    private Integer personId;
    private Integer accId;
    private LocalDateTime changeTime;

    public AccRec(Integer id, Integer balance, Integer personId, Integer accId, LocalDateTime changeTime) {
        this.id = id;
        this.balance = balance;
        this.personId = personId;
        this.accId = accId;
        this.changeTime = changeTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getAccId() {
        return accId;
    }

    public void setAccId(Integer accId) {
        this.accId = accId;
    }

    public LocalDateTime getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(LocalDateTime changeTime) {
        this.changeTime = changeTime;
    }

    @Override
    public String toString() {
        return "AccRec{" +
                "id=" + id +
                ", balance=" + balance +
                ", personId=" + personId +
                ", accId=" + accId +
                ", changeTime=" + changeTime +
                '}';
    }

    @Override
    public Integer takePersonId() {
        return personId;
    }
}
