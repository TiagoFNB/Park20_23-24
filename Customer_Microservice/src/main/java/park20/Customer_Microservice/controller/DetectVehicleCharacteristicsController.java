package park20.Customer_Microservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Customer_Microservice.dto.VehicleDTO;
import park20.Customer_Microservice.service.DetectVehicleCharacteristicsService;

@RestController
public class DetectVehicleCharacteristicsController {

    protected DetectVehicleCharacteristicsService detectVehicleCharacteristicsService;

    public DetectVehicleCharacteristicsController(DetectVehicleCharacteristicsService detectVehicleCharacteristicsService) {
        this.detectVehicleCharacteristicsService = detectVehicleCharacteristicsService;
    }

    @GetMapping(value = "/vehicle-detect")
    public VehicleDTO detect(@RequestParam String licensePlate) {
        try{
            return this.detectVehicleCharacteristicsService.detect(licensePlate);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

}
