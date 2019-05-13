package za.co.discovery.atm.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CURRENCY")
public class Currency implements Serializable {

    @Id
    @Column(name = "CURRENCY_CODE")
    private String currencyCode;

    @Column(name = "DECIMAL_PLACES")
    private Integer decimalPlaces;

    @Column(name = "DESCRIPTION")
    private String description;

    @Transient
    private CurrencyConversionRate currencyConversionRate;

    public Integer getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDecimalPlaces(Integer decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public CurrencyConversionRate getCurrencyConversionRate() {
        return currencyConversionRate;
    }

    public void setCurrencyConversionRate(CurrencyConversionRate currencyConversionRate) {
        this.currencyConversionRate = currencyConversionRate;
    }
}
