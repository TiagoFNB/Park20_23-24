package park20.Customer_Microservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import park20.Customer_Microservice.domain.Vehicle.VehicleCategoryEnum;
import park20.Customer_Microservice.dto.VehicleDTO;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DetectVehicleCharacteristicsServiceTest {

    @Test
    void testDetectVehicleCharacteristics() {
        // Arrange
        String licensePlate = "AB-00-AA";

        DetectVehicleCharacteristicsService service = new DetectVehicleCharacteristicsService();

        // Act
        VehicleDTO vehicleDTO = service.detect(licensePlate);

        // Assert
        assertNotNull(vehicleDTO);
        assertEquals(licensePlate, vehicleDTO.plate);
        assertNotNull(vehicleDTO.category);
        assertNotNull(vehicleDTO.brand);
        assertNotNull(vehicleDTO.model);


        try{
            VehicleCategoryEnum.valueOf(vehicleDTO.category);
        } catch(Exception e) {
            fail("Invalid vehicle category: " + vehicleDTO.category);
        }
    }
}