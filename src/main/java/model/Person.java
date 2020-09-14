package model;

import java.util.ArrayList;


public class Person {

    private Integer id;
    private String name;
    private String token;
    private String password;
    private ArrayList<Acc> Accs = new ArrayList<>();
    private ArrayList<Plan> plans = new ArrayList<>();

    public Person(Integer id, String name, String token, String password) {
        this.id = id;
        this.name = name;
        this.token = token;
        this.password = password;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Acc> getAccs() {
        return Accs;
    }

    public void setAccs(ArrayList<Acc> accs) {
        Accs = accs;
    }

    public ArrayList<Plan> getPlans() {
        return plans;
    }

    public void setPlans(ArrayList<Plan> plans) {
        this.plans = plans;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Accs=" + Accs +
                ", plans=" + plans +
                '}';
    }
}
