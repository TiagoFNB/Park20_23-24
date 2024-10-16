package park20.Customer_Microservice.domain.Vehicle;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VehicleLicensePlateTest {

    @Test
    void testCreateLicensePlate() {
        // Arrange
        String licensePlate = "AA-00-AA";

        // Act
        VehicleLicensePlate plate = new VehicleLicensePlate(licensePlate);


        // Assert
        assertNotNull(plate);
        assertEquals(licensePlate, plate.toString());
    }

    @Test
    void testCreateInvalidLicensePlate() {
        // Arrange
        String invalidLicensePlate1 = "AA-AA-AA";
        String invalidLicensePlate2 = "00-00-00";
        String invalidLicensePlate3 = "00A00A00";
        String invalidLicensePlate4 = "aosdkojdi";

        // Act
        assertThrows(Exception.class, () -> {
            new VehicleLicensePlate(invalidLicensePlate1);
        });
        assertThrows(Exception.class, () -> {
            new VehicleLicensePlate(invalidLicensePlate2);
        });
        assertThrows(Exception.class, () -> {
            new VehicleLicensePlate(invalidLicensePlate3);
        });
        assertThrows(Exception.class, () -> {
            new VehicleLicensePlate(invalidLicensePlate4);
        });
    }
}
