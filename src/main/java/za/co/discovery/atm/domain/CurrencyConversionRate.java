package za.co.discovery.atm.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CURRENCY_CONVERSION_RATE")
public class CurrencyConversionRate implements Serializable {
    @Id
    @Column(name = "CURRENCY_CODE")
    private String currencyCode;
    @Column(name = "CONVERSION_INDICATOR")
    private String conversionIndicator;
    @Column(name = "RATE")
    private Double rate;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getConversionIndicator() {
        return conversionIndicator;
    }

    public void setConversionIndicator(String conversionIndicator) {
        this.conversionIndicator = conversionIndicator;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
