package za.co.discovery.atm.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "CREDIT_CARD_LIMIT")
public class CreditCardLimit implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENT_ACCOUNT_NUMBER")
    private ClientAccount clientAccount;

    @Column(name = "ACCOUNT_LIMIT")
    private BigDecimal accountLimit;

    public ClientAccount getClientAccount() {
        return clientAccount;
    }

    public void setClientAccount(ClientAccount clientAccount) {
        this.clientAccount = clientAccount;
    }

    public BigDecimal getAccountLimit() {
        return accountLimit;
    }

    public void setAccountLimit(BigDecimal accountLimit) {
        this.accountLimit = accountLimit;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CreditCardLimit{");
        sb.append("clientAccount=").append(clientAccount);
        sb.append(", accountLimit=").append(accountLimit);
        sb.append('}');
        return sb.toString();
    }
}
