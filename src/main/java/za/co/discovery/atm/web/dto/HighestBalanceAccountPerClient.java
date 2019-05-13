package za.co.discovery.atm.web.dto;

import java.math.BigDecimal;

public class HighestBalanceAccountPerClient {

    private Integer clientId;
    private String cllientSurname;
    private String clientAccountNumber;
    private String accountDescription;
    private BigDecimal displayBalnace;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getCllientSurname() {
        return cllientSurname;
    }

    public void setCllientSurname(String cllientSurname) {
        this.cllientSurname = cllientSurname;
    }

    public String getClientAccountNumber() {
        return clientAccountNumber;
    }

    public void setClientAccountNumber(String clientAccountNumber) {
        this.clientAccountNumber = clientAccountNumber;
    }

    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }

    public BigDecimal getDisplayBalnace() {
        return displayBalnace;
    }

    public void setDisplayBalnace(BigDecimal displayBalnace) {
        this.displayBalnace = displayBalnace;
    }
}
