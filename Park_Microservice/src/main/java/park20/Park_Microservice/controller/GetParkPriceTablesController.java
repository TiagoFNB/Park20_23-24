package park20.Park_Microservice.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Park_Microservice.dto.PriceTableDTO;
import park20.Park_Microservice.service.GetParkPriceTablesService;

import java.util.List;

@RestController
public class GetParkPriceTablesController {

    protected GetParkPriceTablesService getParkPriceTablesService;

    public GetParkPriceTablesController(GetParkPriceTablesService getParkPriceTablesService) {
        this.getParkPriceTablesService = getParkPriceTablesService;
    }

    @GetMapping(value = "/price-tables")
    public List<PriceTableDTO> getList(@RequestParam String parkId){
        try{
            return this.getParkPriceTablesService.getList(parkId);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
