package za.co.discovery.atm.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class LoginRequest {
    @JsonProperty(value = "timeStamp")
    private Date timeStamp;

    @JsonProperty(value = "cardProfile")
    private CardProfile cardProfile;

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public CardProfile getCardProfile() {
        return cardProfile;
    }

    public void setCardProfile(CardProfile cardProfile) {
        this.cardProfile = cardProfile;
    }
}
