package za.co.discovery.atm.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "CLIENT_ACCOUNT")
public class ClientAccount {
    @Id
    @Column(name = "CLIENT_ACCOUNT_NUMBER")
    private String accountNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ACCOUNT_TYPE_CODE")
    private AccountType accountType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CURRENCY_CODE")
    private Currency currency;

    @Column(name = "DISPLAY_BALANCE")
    private BigDecimal displayBalance;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getDisplayBalance() {
        return displayBalance;
    }

    public void setDisplayBalance(BigDecimal displayBalance) {
        this.displayBalance = displayBalance;
    }
}
