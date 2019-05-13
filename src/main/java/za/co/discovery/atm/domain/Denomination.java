package za.co.discovery.atm.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "DENOMINATION")
public class Denomination {
    @Id
    @GeneratedValue
    @Column(name = "DENOMINATION_ID")
    private Integer denominationId;
    @Column(name = "VALUE")
    private BigDecimal value;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DENOMINATION_TYPE_CODE")
    private DenominationType denominationType;

    public Integer getDenominationId() {
        return denominationId;
    }

    public void setDenominationId(Integer denominationId) {
        this.denominationId = denominationId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public DenominationType getDenominationType() {
        return denominationType;
    }

    public void setDenominationType(DenominationType denominationType) {
        this.denominationType = denominationType;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Denomination{");
        sb.append("denominationId=").append(denominationId);
        sb.append(", value=").append(value);
        sb.append(", denominationType=").append(denominationType);
        sb.append('}');
        return sb.toString();
    }
}
