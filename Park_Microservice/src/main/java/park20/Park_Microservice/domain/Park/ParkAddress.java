package park20.Park_Microservice.domain.Park;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;
import park20.Park_Microservice.shared.ValueObject;

import javax.persistence.Column;

public class ParkAddress implements ValueObject<ParkAddress> {

    @JsonProperty
    @Column(nullable = false)
    private String street;

    @JsonProperty
    @Column(nullable = false)
    private String city;

    @JsonProperty
    @Column(nullable = false)
    private String postalCode;

    @JsonProperty
    @Column(nullable = false)
    private String latitude;

    @JsonProperty
    @Column(nullable = false)
    private String longitude;

    protected ParkAddress() {
    }

    public ParkAddress(final String street, final String city, final String postalCode, final String latitude, final String longitude) {
        Validate.notNull(street);
        Validate.notEmpty(street);

        Validate.notNull(city);
        Validate.notEmpty(city);

        Validate.notNull(postalCode);
        Validate.notEmpty(postalCode);

        Validate.notNull(latitude);
        Validate.notEmpty(latitude);

        Validate.notNull(longitude);
        Validate.notEmpty(longitude);

        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    @Override
    public boolean sameValueAs(ParkAddress other) {
        return false;
    }
}
