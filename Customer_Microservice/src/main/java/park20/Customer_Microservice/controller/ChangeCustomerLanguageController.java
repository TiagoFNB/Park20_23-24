package park20.Customer_Microservice.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Customer_Microservice.dto.ChangeLanguageDTO;
import park20.Customer_Microservice.service.ChangeCustomerLanguageService;
import park20.Customer_Microservice.shared.Utils;

@RestController
public class ChangeCustomerLanguageController {

    ChangeCustomerLanguageService changeCustomerLanguageService;

    public ChangeCustomerLanguageController(ChangeCustomerLanguageService changeCustomerLanguageService) {
        this.changeCustomerLanguageService = changeCustomerLanguageService;
    }

    @PostMapping(value = "/language")
    public void detect(@RequestBody ChangeLanguageDTO dto) {
        try{
            this.changeCustomerLanguageService.change(dto);
            return;
        } catch(Exception e) {
            if(e instanceof DataIntegrityViolationException) {
                e = new Exception(Utils.HumanReadableConstraintViolation((DataIntegrityViolationException) e));
            }

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
