package park20.Customer_Microservice.domain.Customer;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.apache.commons.lang3.Validate;
import park20.Customer_Microservice.domain.ParkyWallet.ParkyWallet;
import park20.Customer_Microservice.domain.Vehicle.Vehicle;
import park20.Customer_Microservice.dto.CustomerDTO;
import park20.Customer_Microservice.shared.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "Customer")
public class Customer implements Entity<Customer> {

    @EmbeddedId
    @JsonUnwrapped
    private CustomerInternalId id;

    @Embedded
    @JsonUnwrapped
    private CustomerEmail email;

    @Embedded
    @JsonUnwrapped
    private CustomerPassword pass;

    @Embedded
    @JsonUnwrapped
    private CustomerUsername username;

    @Embedded
    @JsonUnwrapped
    private CustomerName name;

    @Embedded
    @JsonUnwrapped
    private CustomerNif nif;

    @JsonProperty
    @Column(nullable = false)
    private boolean handicapped;

    @Embedded
    @JsonUnwrapped
    private ParkyWallet wallet;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerPaymentMethod> paymentMethods;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicles;

    @JsonProperty
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CustomerLanguagesEnum language;


    protected Customer() {}

    public Customer(final CustomerInternalId id, final CustomerEmail email, final CustomerPassword pass, final CustomerUsername username, final CustomerName name, final CustomerNif nif, final boolean handicapped) {
        Validate.notNull(id, "CustomerInternalId is required");
        Validate.notNull(email, "CustomerEmail is required");
        Validate.notNull(pass, "CustomerPassword is required");
        Validate.notNull(username, "CustomerUsername is required");
        Validate.notNull(name, "CustomerName is required");
        Validate.notNull(nif, "CustomerNif is required");
        Validate.notNull(handicapped, "Handicapped is required");

        this.id = id;
        this.email = email;
        this.pass = pass;
        this.username = username;
        this.name = name;
        this.nif = nif;
        this.handicapped = handicapped;
        this.paymentMethods = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        this.language = CustomerLanguagesEnum.en;
        this.wallet = new ParkyWallet(0);
    }

    public CustomerInternalId getId() {
        return id;
    }

    public CustomerUsername getUsername() {
        return username;
    }

    public CustomerPassword getPass() {
        return pass;
    }

    public CustomerEmail getEmail() {
        return email;
    }

    public CustomerName getName() {
        return name;
    }

    public CustomerLanguagesEnum getLanguage() {
        return language;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public boolean getHandicapped() {
        return handicapped;
    }

    public ParkyWallet getWallet() { return wallet; }

    public void changeLanguage(CustomerLanguagesEnum lang) {
        this.language = lang;
    }

    public void addPaymentMethod(CustomerPaymentMethod paymentMethod) {
        this.paymentMethods.add(paymentMethod);
        //paymentMethod.setCustomer(this);
    }

    /**
     * Adds vehicle to the list (only used for a new customer)
     * @param vehicle
     */
    public void addVehicle(Vehicle vehicle) {
        vehicle.setCustomer(this);
        this.vehicles.add(vehicle);
    }

    /**
     * Sets the vehicles list.
     * Old vehicles are disabled.
     * @param newVehicles
     */
    public void setVehicles(List<Vehicle> newVehicles) {
        for(Vehicle newVehicle : newVehicles) {
            newVehicle.setCustomer(this);

            //If the vehicle is already in the list update it
           if(vehicles.contains(newVehicle)) {
               int index = vehicles.indexOf(newVehicle);
               vehicles.get(index).update(newVehicle);
           }
           //If the vehicle is not in the list add it
           else {
             vehicles.add(newVehicle);
           }
        }

        //Disable the removed vehicles
        List<Vehicle> removedVehicles = new ArrayList<>(vehicles);
        removedVehicles.removeAll(newVehicles);
        removedVehicles.forEach(Vehicle::disable);
    }

    public void setPaymentMethods(List<CustomerPaymentMethod> newPaymentsMethods){
        for(CustomerPaymentMethod newPaymentMethod : newPaymentsMethods){
            if(paymentMethods.contains(newPaymentMethod)){
                int index = paymentMethods.indexOf(newPaymentMethod);
                paymentMethods.get(index).update(newPaymentMethod);
            }
            else {
                paymentMethods.add(newPaymentMethod);
            }
        }

        List<CustomerPaymentMethod> removedPaymentMethods = new ArrayList<>(paymentMethods);
        removedPaymentMethods.removeAll(newPaymentsMethods);
        removedPaymentMethods.forEach((CustomerPaymentMethod::disable));

    }

    @Override
    public boolean sameIdentityAs(final Customer other) {
        return other != null && id.sameValueAs(other.id);
    }

    /**
     * @param object to compare
     * @return True if they have the same identity
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        final Customer other = (Customer) object;
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

    public CustomerDTO toDto() {
        CustomerDTO dto = new CustomerDTO();
        dto.id = id.toString();
        dto.email = email.toString();
        dto.username = username.toString();
        dto.handicapped = handicapped;
        dto.nif = nif.toString();
        dto.name = name.toString();
        dto.language = language.toString();
        dto.wallet = this.wallet.toDto();
        return dto;
    }

}
