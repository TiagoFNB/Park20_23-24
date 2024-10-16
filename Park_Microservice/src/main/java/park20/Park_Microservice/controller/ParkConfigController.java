package park20.Park_Microservice.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Park_Microservice.dto.ParkWithSpotsAndParkyDTO;
import park20.Park_Microservice.service.ParkConfigService;

@RestController
public class ParkConfigController {

    ParkConfigService parkConfigService;

    public ParkConfigController(ParkConfigService parkConfigService) {
        this.parkConfigService = parkConfigService;
    }

    @PostMapping(value = "/parkConfig")
    public void save(@RequestBody ParkWithSpotsAndParkyDTO dto) {
        try{
            this.parkConfigService.save(dto);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
