package park20.Customer_Microservice.domain.Customer;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import park20.Customer_Microservice.shared.ValueObject;


@javax.persistence.Entity
@Table(name = "PaymentMethod")
public class CustomerPaymentMethod implements ValueObject<CustomerPaymentMethod> {

    @EmbeddedId
    @JsonUnwrapped
    private PaymentMethodInternalId id;


    @JsonUnwrapped
    private String phoneNumber;

    @JsonProperty
    @Column(nullable = false)
    private boolean active;

   
    protected CustomerPaymentMethod() {}

    /**
     * Constructor.
     *
     * @param nif Designation string.
     */
    public CustomerPaymentMethod(final String phoneNumber, final PaymentMethodInternalId id) {
        Validate.notNull(phoneNumber);
        Validate.notEmpty(phoneNumber);
        Validate.notNull(id, "CustomerInternalId is required");
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.active = true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerPaymentMethod other = (CustomerPaymentMethod) o;

        return sameValueAs(other);
    }
    
    @Override
    public int hashCode() {
        return phoneNumber.hashCode();
    }

   
    @Override
    public boolean sameValueAs(CustomerPaymentMethod other) {
        return other != null && this.phoneNumber.equals(other.phoneNumber); 
    }

    public void update(CustomerPaymentMethod v) {
        this.phoneNumber = v.phoneNumber;
    }


    public void disable() {
        this.active = false;
    }


    @Override
    public String toString() {
        return phoneNumber;
    }
    
}
