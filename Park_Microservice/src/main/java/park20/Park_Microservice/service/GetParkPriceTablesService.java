package park20.Park_Microservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import park20.Park_Microservice.domain.Park.PriceTable;
import park20.Park_Microservice.dto.PriceTableDTO;
import park20.Park_Microservice.repository.ParkRepository;
import park20.Park_Microservice.domain.Park.Park;
import park20.Park_Microservice.domain.Park.ParkSpot;
import park20.Park_Microservice.domain.Vehicle.VehicleCategory;
import park20.Park_Microservice.domain.Vehicle.VehicleCategoryEnum;
import park20.Park_Microservice.dto.ParkAddressDTO;
import park20.Park_Microservice.dto.ParkWithDistanceDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetParkPriceTablesService {

    private final ParkRepository parkRepository;

    public GetParkPriceTablesService(ParkRepository parkRepository) {
        this.parkRepository = parkRepository;
    }

    public List<PriceTableDTO> getList(String parkId){

        Park park = this.parkRepository.getParkByDesignation_Designation(parkId);
        List<PriceTableDTO> dtoList = new ArrayList<>();
        List<PriceTable> priceTables = park.getPriceTables();
        for(PriceTable p:priceTables){
            dtoList.add(p.toDto());
        }

        return dtoList;
    }



}
