package ru.vapima.butjet.model;


public class AccInfoEntity {
    Integer id;
    Integer personId;
    String name;
    String nameInfo;
    String description;
    Boolean active;
public AccInfoEntity(Integer id, Integer personId, String name, String nameInfo, String description,  Boolean active) {
        this.id = id;
        this.personId = personId;
        this.name = name;
        this.nameInfo = nameInfo;
        this.description = description;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameInfo() {
        return nameInfo;
    }

    public void setNameInfo(String nameInfo) {
        this.nameInfo = nameInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "AccInfoEntity{" +
                "id=" + id +
                ", personId=" + personId +
                ", nameAcc='" + name + '\'' +
                ", name='" + nameInfo + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}';
    }
}
