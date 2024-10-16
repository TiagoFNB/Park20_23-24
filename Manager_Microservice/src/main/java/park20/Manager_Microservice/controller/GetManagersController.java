package park20.Manager_Microservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Manager_Microservice.domain.Manager.Manager;
import park20.Manager_Microservice.service.GetManagersService;

import java.util.List;

@RestController
public class GetManagersController {

    GetManagersService getManagersService;

    public GetManagersController(GetManagersService getManagersService) {
        this.getManagersService = getManagersService;
    }

    @GetMapping(value = "/managers")
    public List<Manager> get() {
        try {
            return this.getManagersService.get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
