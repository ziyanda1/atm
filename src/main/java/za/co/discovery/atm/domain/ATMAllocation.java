package za.co.discovery.atm.domain;

import javax.persistence.*;

@Entity
@Table(name = "ATM_ALLOCATION")
public class ATMAllocation {
    @Id
    @GeneratedValue
    @Column(name = "ATM_ALLOCATION_ID")
    private Integer atmAllocationId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ATM_ID")
    private ATM atm;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DENOMINATION_ID")
    private Denomination denomination;

    @Column(name = "COUNT")
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getAtmAllocationId() {
        return atmAllocationId;
    }

    public void setAtmAllocationId(Integer atmAllocationId) {
        this.atmAllocationId = atmAllocationId;
    }

    public ATM getAtm() {
        return atm;
    }

    public void setAtm(ATM atm) {
        this.atm = atm;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public void setDenomination(Denomination denomination) {
        this.denomination = denomination;
    }

    @Override
    public String toString() {
        return "ATMAllocation{" +
                "atmAllocationId=" + atmAllocationId +
                ", atm=" + atm +
                ", denomination=" + denomination +
                '}';
    }
}
