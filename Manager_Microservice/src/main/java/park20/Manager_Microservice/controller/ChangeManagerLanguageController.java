package park20.Manager_Microservice.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Manager_Microservice.dto.ChangeLanguageDTO;
import park20.Manager_Microservice.service.ChangeManagerLanguageService;
import park20.Manager_Microservice.shared.Utils;

@RestController
public class ChangeManagerLanguageController {

    ChangeManagerLanguageService changeManagerLanguageService;

    public ChangeManagerLanguageController(ChangeManagerLanguageService changeManagerLanguageService) {
        this.changeManagerLanguageService = changeManagerLanguageService;
    }

    @PostMapping(value = "/language")
    public void detect(@RequestBody ChangeLanguageDTO dto) {
        try{
            this.changeManagerLanguageService.change(dto);
            return;
        } catch(Exception e) {
            if(e instanceof DataIntegrityViolationException) {
                e = new Exception(Utils.HumanReadableConstraintViolation((DataIntegrityViolationException) e));
            }

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
