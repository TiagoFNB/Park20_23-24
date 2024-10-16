package park20.Customer_Microservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Customer_Microservice.domain.Customer.CustomerInternalId;
import park20.Customer_Microservice.dto.CustomerDTO;
import park20.Customer_Microservice.service.GetCustomerService;
import park20.Customer_Microservice.shared.Utils;

@RestController
public class GetLoggedInCustomerController {

    GetCustomerService getCustomerService;

    public GetLoggedInCustomerController(GetCustomerService getCustomerService) {
        this.getCustomerService = getCustomerService;
    }

    @GetMapping(value = "/customer-logged")
    public CustomerDTO get() {
        try{
            return this.getCustomerService.get(new CustomerInternalId(Utils.getUserDetails().getId()));
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
