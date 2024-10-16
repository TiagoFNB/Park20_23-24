package park20.Manager_Microservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Manager_Microservice.domain.Manager.ManagerInternalId;
import park20.Manager_Microservice.dto.ManagerDTO;
import park20.Manager_Microservice.service.GetManagerService;
import park20.Manager_Microservice.shared.Utils;

@RestController
public class GetLoggedInManagerController {

    GetManagerService getManagerService;

    public GetLoggedInManagerController(GetManagerService getManagerService) {
        this.getManagerService = getManagerService;
    }

    @GetMapping(value = "/manager-logged")
    public ManagerDTO get() {
        try {
            return this.getManagerService.get(new ManagerInternalId(Utils.getUserDetails().getId()));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

}
