package park20.Park_Microservice.domain.Park;


import com.fasterxml.jackson.annotation.JsonProperty;
import park20.Park_Microservice.shared.ValueObject;

import javax.persistence.*;

@Embeddable
public class ParkWelcomeMessage implements ValueObject<ParkWelcomeMessage> {


    @JsonProperty
    @Column(nullable = false)
    private String welcomePortuguese;

    @JsonProperty
    @Column(nullable = false)
    private String welcomeEnglish;

    @JsonProperty
    @Column(nullable = false)
    private String welcomeSpanish;


    protected ParkWelcomeMessage() {
    }

    public ParkWelcomeMessage(String portuguese, String english, String spanish) {
        this.welcomePortuguese = portuguese;
        this.welcomeEnglish = english;
        this.welcomeSpanish = spanish;
    }

    public String getWelcomePortuguese() {
        return welcomePortuguese;
    }

    public String getWelcomeEnglish() {
        return welcomeEnglish;
    }

    public String getWelcomeSpanish() {
        return welcomeSpanish;
    }

    @Override
    public boolean sameValueAs(ParkWelcomeMessage other) {
        return false;
    }
}
