package za.co.discovery.atm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENT_TYPE")
public class ClientType {
    @Id
    @Column(name = "CLIENT_TYPE_CODE")
    private String clientTypeCode;

    @Column(name = "DESCRIPTION")
    private String description;

    public String getClientTypeCode() {
        return clientTypeCode;
    }

    public void setClientTypeCode(String clientTypeCode) {
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
        final StringBuffer sb = new StringBuffer("ClientType{");
        sb.append("clientTypeCode='").append(clientTypeCode).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
