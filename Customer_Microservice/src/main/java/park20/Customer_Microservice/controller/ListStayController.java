package park20.Customer_Microservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Customer_Microservice.domain.Customer.CustomerInternalId;
import park20.Customer_Microservice.domain.Stay.Stay;
import park20.Customer_Microservice.dto.CustomerDTO;
import park20.Customer_Microservice.dto.StayDTO;
import park20.Customer_Microservice.service.GetCustomerService;
import park20.Customer_Microservice.service.ListStayService;
import park20.Customer_Microservice.shared.Utils;

import java.util.List;

@RestController
public class ListStayController {

    ListStayService listStayService;

    public ListStayController(ListStayService listStayService) {
        this.listStayService = listStayService;
    }

    @GetMapping(value = "/stays")
    public List<StayDTO> get() {
        try{
            return this.listStayService.get();
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
