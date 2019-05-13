package za.co.discovery.atm.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CLIENT")
public class Client {
    @Id
    @GeneratedValue
    @Column(name = "CLIENT_ID")
    private Integer clientId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "DOB")
    private Date dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENT_SUB_TYPE_CODE")
    private ClientSubType clientSubType;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ClientSubType getClientSubType() {
        return clientSubType;
    }

    public void setClientSubType(ClientSubType clientSubType) {
        this.clientSubType = clientSubType;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", clientSubType=" + clientSubType +
                '}';
    }
}
