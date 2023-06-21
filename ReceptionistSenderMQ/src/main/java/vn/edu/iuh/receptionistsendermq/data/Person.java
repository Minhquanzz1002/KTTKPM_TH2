package vn.edu.iuh.receptionistsendermq.data;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@XmlType(propOrder = {"id", "name", "birth"})
public class Person implements Serializable {
    private long id;
    private String name;
    private Date birth;

    public Person() {
    }

    public Person(long id, String name, Date birth) {
        this.id = id;
        this.name = name;
        this.birth = birth;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                '}';
    }
}
