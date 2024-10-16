package park20.Park_Microservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Park_Microservice.domain.Park.Park;
import park20.Park_Microservice.dto.ParkWithSpotsAndParkyDTO;
import park20.Park_Microservice.dto.ParkWithDistanceDTO;
import park20.Park_Microservice.service.GetParksService;

import java.util.List;

@RestController
public class GetParksController {

    protected GetParksService getParksService;

    public GetParksController(GetParksService getParksService) {
        this.getParksService = getParksService;
    }

    @GetMapping(value = "/parks")
    public List<ParkWithDistanceDTO> get(){
        try{
            return this.getParksService.get();
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/park")
    public ParkWithSpotsAndParkyDTO getParkByID(@RequestParam String id){
        try{
            return this.getParksService.getParkByID(id);
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
