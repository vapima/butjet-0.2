package ru.vapima.butjet.model;

public class Person implements PersonID {
    private Integer id;
    private String name; //This is E-MAIL FORMAT
    private String token;
    private String password;

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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public Integer takePersonId() {
        return id;
    }
}
