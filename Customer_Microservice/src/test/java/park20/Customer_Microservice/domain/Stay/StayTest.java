package park20.Customer_Microservice.domain.Stay;

import org.apache.commons.lang3.Validate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import park20.Customer_Microservice.domain.Vehicle.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class StayTest {

    @Test
    void testCreateStay() {
        // Arrange
        String licensePlate = "AA-00-AA";
        String brandStr = "Honda";
        String modelStr = "Civic";

        VehicleCategoryEnum categoryStr = VehicleCategoryEnum.Gas;

        String parkIdStr = "p1";
        String slotIdStr = "ps1";
        // Act
        VehicleInternalId idV = VehicleInternalId.genNewId();
        VehicleLicensePlate plate = new VehicleLicensePlate(licensePlate);
        VehicleBrand brand = new VehicleBrand(brandStr);
        VehicleModel model = new VehicleModel(modelStr);
        VehicleCategory category = new VehicleCategory(categoryStr);

        Vehicle v = new Vehicle(idV,plate,brand,model,category);

        StayInternalId id = StayInternalId.genNewId();
        StayStartTime stayStartTime = new StayStartTime(LocalDateTime.now());
        StayParkId parkId = new StayParkId(parkIdStr);
        StaySlotId slotId = new StaySlotId(slotIdStr);
        Stay stay = new Stay(id,stayStartTime,parkId,v,slotId);


        // Assert
        assertNotNull(plate);


    }

    @Test
    void testCreateInvalidStay() {
        // Arrange
        String licensePlate = "AA-00-AA";
        String brandStr = "Honda";
        String modelStr = "Civic";

        VehicleCategoryEnum categoryStr = VehicleCategoryEnum.Gas;

        String parkIdStr = "p1";
        String slotIdStr = "ps1";
        // Act
        VehicleInternalId idV = VehicleInternalId.genNewId();
        VehicleLicensePlate plate = new VehicleLicensePlate(licensePlate);
        VehicleBrand brand = new VehicleBrand(brandStr);
        VehicleModel model = new VehicleModel(modelStr);
        VehicleCategory category = new VehicleCategory(categoryStr);

        Vehicle v = new Vehicle(idV,plate,brand,model,category);

        StayInternalId id = StayInternalId.genNewId();
        StayStartTime stayStartTime = new StayStartTime(LocalDateTime.now());
        StayParkId parkId = new StayParkId(parkIdStr);
        StaySlotId slotId = new StaySlotId(slotIdStr);


        assertThrows(Exception.class, () -> {
            new Stay(id,stayStartTime,parkId,v,null);
        });

    }
}
