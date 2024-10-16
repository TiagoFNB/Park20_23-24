package park20.Customer_Microservice.domain.Vehicle;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class VehicleTest {

    @Test
    void testCreateVehicle() {
        // Arrange
        String licensePlate = "AA-00-AA";
        String brandStr = "Honda";
        String modelStr = "Civic";
        VehicleCategoryEnum categoryStr = VehicleCategoryEnum.Gas;

        // Act
        VehicleInternalId id = VehicleInternalId.genNewId();
        VehicleLicensePlate plate = new VehicleLicensePlate(licensePlate);
        VehicleBrand brand = new VehicleBrand(brandStr);
        VehicleModel model = new VehicleModel(modelStr);
        VehicleCategory category = new VehicleCategory(categoryStr);

        Vehicle v = new Vehicle(id,plate,brand,model,category);


        // Assert
        assertNotNull(v);
    }

    @Test
    void testCreateInvalidVehicle() {
        // Arrange
        String licensePlate = "AA-00-AA";
        String brandStr = "Honda";
        String modelStr = "Civic";
        VehicleCategoryEnum categoryStr = VehicleCategoryEnum.Gas;

        // Act
        VehicleInternalId id = VehicleInternalId.genNewId();
        VehicleLicensePlate plate = new VehicleLicensePlate(licensePlate);
        VehicleBrand brand = new VehicleBrand(brandStr);
        VehicleModel model = new VehicleModel(modelStr);
        VehicleCategory category = new VehicleCategory(categoryStr);

        assertThrows(Exception.class, () -> {
            new Vehicle(null,plate,brand,model,category);
        });

    }
}
