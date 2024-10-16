package park20.Customer_Microservice.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Customer_Microservice.dto.VehicleParkTransitionDTO;
import park20.Customer_Microservice.service.VehicleExitService;
import park20.Customer_Microservice.shared.Utils;

@RestController
public class VehicleExitController {
    protected VehicleExitService vehicleExitService;

    public VehicleExitController(VehicleExitService vehicleExitService) {
        this.vehicleExitService = vehicleExitService;
    }

    @PostMapping(value = "/vehicle-exit")
    public Object enter(@RequestBody VehicleParkTransitionDTO transitionDTO) {
        try{
            return this.vehicleExitService.exit(transitionDTO);
        } catch(Exception e) {
            if(e instanceof DataIntegrityViolationException) {
                e = new Exception(Utils.HumanReadableConstraintViolation((DataIntegrityViolationException) e));
            }

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
