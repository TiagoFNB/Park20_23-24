package park20.Customer_Microservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Customer_Microservice.dto.VehicleListDTO;
import park20.Customer_Microservice.service.SaveVehicleListService;

@RestController
public class SaveVehicleListController {
    SaveVehicleListService saveVehicleListService;

    public SaveVehicleListController(SaveVehicleListService saveVehicleListService) {
        this.saveVehicleListService = saveVehicleListService;
    }

    @PostMapping(value = "/vehicle")
    public void save(@RequestBody VehicleListDTO vehiclesDTO) {
        try{
            this.saveVehicleListService.save(vehiclesDTO);
            return;
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
