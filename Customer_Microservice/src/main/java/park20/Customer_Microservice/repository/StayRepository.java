package park20.Customer_Microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import park20.Customer_Microservice.domain.Customer.Customer;
import park20.Customer_Microservice.domain.Customer.CustomerInternalId;
import park20.Customer_Microservice.domain.Customer.CustomerUsername;
import park20.Customer_Microservice.domain.Stay.Stay;
import park20.Customer_Microservice.domain.Stay.StayInternalId;
import park20.Customer_Microservice.domain.Vehicle.Vehicle;
import park20.Customer_Microservice.domain.Vehicle.VehicleLicensePlate;

import java.util.List;

public interface StayRepository extends JpaRepository<Stay, String> {

    Stay getStayById(StayInternalId id);
    List<Stay> getStaysByVehicleAndStayEndTimeIsNotNull(Vehicle v);

    Stay findTopByStayEndTimeNullAndVehicle(Vehicle v);
}
