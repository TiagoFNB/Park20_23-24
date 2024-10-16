package park20.Customer_Microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import park20.Customer_Microservice.domain.Stay.Stay;
import park20.Customer_Microservice.domain.Vehicle.Vehicle;
import park20.Customer_Microservice.domain.Vehicle.VehicleInternalId;
import park20.Customer_Microservice.domain.Vehicle.VehicleLicensePlate;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    Vehicle getVehicleById(VehicleInternalId id);
    Vehicle getVehicleByPlate(VehicleLicensePlate lp);
}
