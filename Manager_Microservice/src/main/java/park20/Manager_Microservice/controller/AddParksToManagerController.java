package park20.Manager_Microservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Manager_Microservice.dto.AddParksToManagerDTO;
import park20.Manager_Microservice.service.AddParksToManagerService;

@RestController
public class AddParksToManagerController {

    AddParksToManagerService addParksToManagerService;

    public AddParksToManagerController(AddParksToManagerService addParksToManagerService) {
        this.addParksToManagerService = addParksToManagerService;
    }

    @PostMapping(value = "/manager-park")
    public void add(@RequestBody AddParksToManagerDTO addParkToManagerDTO) {
        try {
            this.addParksToManagerService.add(addParkToManagerDTO.id, addParkToManagerDTO.parks);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

}