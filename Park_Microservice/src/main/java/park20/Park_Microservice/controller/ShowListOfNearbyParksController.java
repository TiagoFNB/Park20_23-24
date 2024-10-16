package park20.Park_Microservice.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Park_Microservice.dto.ParkWithDistanceDTO;
import park20.Park_Microservice.service.ShowListOfNearbyParksService;

import java.util.List;

@RestController
public class ShowListOfNearbyParksController {

    protected ShowListOfNearbyParksService showListOfNearbyParksService;

    public ShowListOfNearbyParksController(ShowListOfNearbyParksService showListOfNearbyParksService) {
        this.showListOfNearbyParksService = showListOfNearbyParksService;
    }

    @GetMapping(value = "/showNearbyParks")
    public List<ParkWithDistanceDTO> showListOfNearbyParks(@RequestParam String latitude, String longitude){
        try{
            return this.showListOfNearbyParksService.getListWithDistance(latitude, longitude);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
