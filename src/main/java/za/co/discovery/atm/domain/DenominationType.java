package za.co.discovery.atm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DENOMINATION_TYPE")
public class DenominationType {
    @Id
    @Column(name = "DENOMINATION_TYPE_CODE")
    private String denominationTypeCode;
    @Column(name = "DESCRIPTION")
    private String description;

    public String getDenominationTypeCode() {
        return denominationTypeCode;
    }

    public void setDenominationTypeCode(String denominationTypeCode) {
        this.denominationTypeCode = denominationTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DenominationType{");
        sb.append("denominationTypeCode='").append(denominationTypeCode).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
