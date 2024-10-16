package park20.Customer_Microservice.domain.Vehicle;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.apache.commons.lang3.Validate;
import park20.Customer_Microservice.domain.Customer.Customer;
import park20.Customer_Microservice.dto.VehicleDTO;
import park20.Customer_Microservice.shared.Entity;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "Vehicle")
public class Vehicle implements Entity<Vehicle> {

    @EmbeddedId
    @JsonUnwrapped
    private VehicleInternalId id;

    @Embedded
    @JsonUnwrapped
    private VehicleLicensePlate plate;

    @Embedded
    @JsonUnwrapped
    private VehicleBrand brand;

    @Embedded
    @JsonUnwrapped
    private VehicleModel model;

    @Embedded
    @JsonUnwrapped
    private VehicleCategory category;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * Boolean to determine if the vehicle is enabled or disabled
     */
    @JsonProperty
    @Column(nullable = false)
    private boolean active;

    protected Vehicle() {}

    public Vehicle(final VehicleInternalId id, final VehicleLicensePlate plate, final VehicleBrand brand, final VehicleModel model, final VehicleCategory category) {
        Validate.notNull(id, "VehicleInternalId is required");
        Validate.notNull(plate, "VehicleLicensePlate is required");
        Validate.notNull(brand, "VehicleBrand is required");
        Validate.notNull(model, "VehicleModel is required");
        Validate.notNull(category, "VehicleCategory is required");
        Validate.notNull(category, "Customer is required");

        this.id = id;
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.active = true;
    }

    public VehicleInternalId getId() {
        return id;
    }

    public VehicleLicensePlate getLicensePlate() {
        return plate;
    }

    public VehicleBrand getBrand() {
        return brand;
    }

    public VehicleModel getModel() {
        return model;
    }

    public VehicleCategory getCategory() {
        return category;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void disable() {
        this.active = false;
    }

    public void update(Vehicle v) {
        this.plate = v.plate;
        this.category = v.category;
        this.brand = v.brand;
        this.model = v.model;
        this.customer = v.customer;
    }

    @Override
    public boolean sameIdentityAs(final Vehicle other) {
        return other != null && (id.sameValueAs(other.id) || plate.sameValueAs(other.plate));
    }

    /**
     * @param object to compare
     * @return True if they have the same identity
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        final Vehicle other = (Vehicle) object;
        return sameIdentityAs(other);
    }

    /**
     * @return Hash code of id
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id.toString();
    }

    public VehicleDTO toDto() {
        VehicleDTO dto = new VehicleDTO();
        dto.id = id.toString();
        dto.plate = plate.toString();
        dto.brand = brand.toString();
        dto.model = model.toString();
        dto.category = category.toString();
        return dto;
    }

}