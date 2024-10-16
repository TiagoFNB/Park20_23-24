package park20.Customer_Microservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import park20.Customer_Microservice.dto.RankCustomerUsageDTO;
import park20.Customer_Microservice.dto.RankCustomerUsageRequestDTO;
import park20.Customer_Microservice.dto.StayDTO;
import park20.Customer_Microservice.service.RankCustomersParkUsageService;

import java.util.List;

@RestController
public class RankCustomersParkUsageController {

    RankCustomersParkUsageService rankCustomersParkUsageService;

    public RankCustomersParkUsageController(RankCustomersParkUsageService rankCustomersParkUsageService) {
        this.rankCustomersParkUsageService = rankCustomersParkUsageService;
    }

    @GetMapping(value = "/rank-customers-usage")
    public List<RankCustomerUsageDTO> add(@RequestParam String[] parks) {
        try {
            return this.rankCustomersParkUsageService.getList(parks);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

}