package za.co.discovery.atm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_TYPE")
public class AccountType {
    @Id
    @Column(name = "ACCOUNT_TYPE_CODE")
    private String accountTypeCode;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TRANSACTIONAL")
    private boolean transactional;

    public String getAccountTypeCode() {
        return accountTypeCode;
    }

    public void setAccountTypeCode(String accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isTransactional() {
        return transactional;
    }

    public void setTransactional(boolean transactional) {
        this.transactional = transactional;
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "accountTypeCode='" + accountTypeCode + '\'' +
                ", description='" + description + '\'' +
                ", transactional=" + transactional +
                '}';
    }
}
