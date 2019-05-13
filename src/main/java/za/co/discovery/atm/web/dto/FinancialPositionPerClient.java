package za.co.discovery.atm.web.dto;

import java.math.BigDecimal;

public class FinancialPositionPerClient {
    private String client;
    private BigDecimal loanBalance;
    private BigDecimal transactionalBalance;
    private BigDecimal netPosition;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public BigDecimal getLoanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(BigDecimal loanBalance) {
        this.loanBalance = loanBalance;
    }

    public BigDecimal getTransactionalBalance() {
        return transactionalBalance;
    }

    public void setTransactionalBalance(BigDecimal transactionalBalance) {
        this.transactionalBalance = transactionalBalance;
    }

    public BigDecimal getNetPosition() {
        return netPosition;
    }

    public void setNetPosition(BigDecimal netPosition) {
        this.netPosition = netPosition;
    }
}
