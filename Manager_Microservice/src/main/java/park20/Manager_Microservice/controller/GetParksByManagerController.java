package park20.Manager_Microservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Manager_Microservice.service.GetParksByManagerService;

import java.util.List;

@RestController
public class GetParksByManagerController {

    GetParksByManagerService getParksByManagerService;

    public GetParksByManagerController(GetParksByManagerService getParksByManagerService) {
        this.getParksByManagerService = getParksByManagerService;
    }

    @GetMapping(value = "/manager-parks")
    public List<String> get(@RequestParam String username) {
        try {
            return this.getParksByManagerService.get(username);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
