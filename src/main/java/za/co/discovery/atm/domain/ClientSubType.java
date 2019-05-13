package za.co.discovery.atm.domain;

import javax.persistence.*;

@Entity
@Table(name = "CLIENT_SUB_TYPE")
public class ClientSubType {
    @Id
    @Column(name = "CLIENT_SUB_TYPE_CODE")
    private String clientSubTypeCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENT_TYPE_CODE")
    private ClientType clientTypeCode;

    @Column(name = "DESCRIPTION")
    private String description;

    public String getClientSubTypeCode() {
        return clientSubTypeCode;
    }

    public void setClientSubTypeCode(String clientSubTypeCode) {
        this.clientSubTypeCode = clientSubTypeCode;
    }

    public ClientType getClientTypeCode() {
        return clientTypeCode;
    }

    public void setClientTypeCode(ClientType clientTypeCode) {
        this.clientTypeCode = clientTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ClientSubType{" +
                "clientSubTypeCode='" + clientSubTypeCode + '\'' +
                ", clientTypeCode=" + clientTypeCode +
                ", description='" + description + '\'' +
                '}';
    }
}
