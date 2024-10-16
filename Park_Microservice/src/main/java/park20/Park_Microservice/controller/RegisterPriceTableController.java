package park20.Park_Microservice.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Park_Microservice.dto.PriceTableDTO;
import park20.Park_Microservice.dto.RegisterPriceTableResponseDTO;
import park20.Park_Microservice.service.RegisterPriceTableService;
import park20.Park_Microservice.shared.Utils;


@RestController
public class RegisterPriceTableController {

    RegisterPriceTableService registerPriceTableService;

    public RegisterPriceTableController(RegisterPriceTableService registerPriceTableService) {
        this.registerPriceTableService = registerPriceTableService;
    }

    @PostMapping(value = "/register-pricetable")
    public RegisterPriceTableResponseDTO detect(@RequestBody PriceTableDTO priceTableDTO) {
        try{
            return this.registerPriceTableService.register(priceTableDTO);
        } catch(Exception e) {
            if(e instanceof DataIntegrityViolationException) {
                e = new Exception(Utils.HumanReadableConstraintViolation((DataIntegrityViolationException) e));
            }

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
