package za.co.discovery.atm.domain;

import javax.persistence.*;

@Entity
@Table(name = "ATM")
public class ATM {
    @Id
    @GeneratedValue
    @Column(name = "ATM_ID")
    private Integer atmID;
    @Column(name = "NAME")
    private String name;
    @Column(name = "LOCATION")
    private String location;

    public Integer getAtmID() {
        return atmID;
    }

    public void setAtmID(Integer atmID) {
        this.atmID = atmID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "ATM{" +
                "atmID=" + atmID +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
