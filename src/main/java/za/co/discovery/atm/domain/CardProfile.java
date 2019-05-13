package za.co.discovery.atm.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardProfile {
    @JsonProperty(value = "number")
    private Long number;
    @JsonProperty(value = "pin")
    private Long pin;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getPin() {
        return pin;
    }

    public void setPin(Long pin) {
        this.pin = pin;
    }
}
