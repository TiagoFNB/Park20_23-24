package park20.Customer_Microservice.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Customer_Microservice.dto.AssignCoinsDTO;
import park20.Customer_Microservice.service.BulkAssignParkyCoinsService;
import park20.Customer_Microservice.shared.Utils;

@RestController
public class BulkAssignParkyCoinsController {

    BulkAssignParkyCoinsService bulkAssignParkyCoinsService;

    public BulkAssignParkyCoinsController(BulkAssignParkyCoinsService bulkAssignParkyCoinsService) {
        this.bulkAssignParkyCoinsService = bulkAssignParkyCoinsService;
    }

    @PostMapping(value = "/coins-assign")
    public void assign(@RequestBody AssignCoinsDTO dto) {
        try{
            this.bulkAssignParkyCoinsService.assign(dto);
            return;
        } catch(Exception e) {
            if(e instanceof DataIntegrityViolationException) {
                e = new Exception(Utils.HumanReadableConstraintViolation((DataIntegrityViolationException) e));
            }

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

}
