package park20.Customer_Microservice.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Customer_Microservice.dto.VehicleParkTransitionDTO;
import park20.Customer_Microservice.service.VehicleEntryService;
import park20.Customer_Microservice.shared.Utils;

@RestController
public class VehicleEntryController {
    protected VehicleEntryService vehicleEntryService;

    public VehicleEntryController(VehicleEntryService vehicleEntryService) {
        this.vehicleEntryService = vehicleEntryService;
    }

    @PostMapping(value = "/vehicle-entry")
    public Object enter(@RequestBody VehicleParkTransitionDTO transitionDTO) {
        try{
            return this.vehicleEntryService.enter(transitionDTO);
        } catch(Exception e) {
            if(e instanceof DataIntegrityViolationException) {
                e = new Exception(Utils.HumanReadableConstraintViolation((DataIntegrityViolationException) e));
            }

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
