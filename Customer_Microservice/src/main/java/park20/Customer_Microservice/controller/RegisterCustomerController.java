package park20.Customer_Microservice.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Customer_Microservice.dto.AuthResponseDTO;
import park20.Customer_Microservice.dto.CustomerDTO;
import park20.Customer_Microservice.service.RegisterCustomerService;
import park20.Customer_Microservice.shared.Utils;


@RestController
public class RegisterCustomerController {

    RegisterCustomerService registerCustomerService;

    public RegisterCustomerController(RegisterCustomerService registerCustomerService) {
        this.registerCustomerService = registerCustomerService;
    }

    @PostMapping(value = "/register")
    public AuthResponseDTO detect(@RequestBody CustomerDTO customerDTO) {
        try{
            return this.registerCustomerService.register(customerDTO);
        } catch(Exception e) {
            if(e instanceof DataIntegrityViolationException) {
                e = new Exception(Utils.HumanReadableConstraintViolation((DataIntegrityViolationException) e));
            }

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
