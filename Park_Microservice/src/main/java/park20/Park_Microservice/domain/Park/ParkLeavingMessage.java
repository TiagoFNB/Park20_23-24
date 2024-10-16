package park20.Park_Microservice.domain.Park;

import com.fasterxml.jackson.annotation.JsonProperty;
import park20.Park_Microservice.shared.ValueObject;

import javax.persistence.*;

@Embeddable
public class ParkLeavingMessage implements ValueObject<ParkLeavingMessage> {


    @JsonProperty
    @Column(nullable = false)
    private String leavingPortuguese;

    @JsonProperty
    @Column(nullable = false)
    private String leavingEnglish;

    @JsonProperty
    @Column(nullable = false)
    private String leavingSpanish;

    protected ParkLeavingMessage() {
    }

    public ParkLeavingMessage(String portuguese, String english, String spanish) {
        this.leavingPortuguese = portuguese;
        this.leavingEnglish = english;
        this.leavingSpanish = spanish;
    }

    public String getLeavingPortuguese() {
        return leavingPortuguese;
    }

    public String getLeavingEnglish() {
        return leavingEnglish;
    }

    public String getLeavingSpanish() {
        return leavingSpanish;
    }

    @Override
    public boolean sameValueAs(ParkLeavingMessage other) {
        return false;
    }
}
