package vn.edu.iuh.receptionistsendermq.data;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.io.Serializable;

@XmlRootElement
@XmlType(propOrder = {"id", "identityCardId", "name", "address"})
public class Patient implements Serializable {
    private long id;
    private String identityCardId;
    private String name;
    private String address;

    public Patient() {
    }

    public Patient(long id, String identityCardId, String name, String address) {
        this.id = id;
        this.identityCardId = identityCardId;
        this.name = name;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdentityCardId() {
        return identityCardId;
    }

    public void setIdentityCardId(String identityCardId) {
        this.identityCardId = identityCardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", identityCardId='" + identityCardId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
