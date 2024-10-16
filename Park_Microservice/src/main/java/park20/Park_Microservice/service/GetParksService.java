package park20.Park_Microservice.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import park20.Park_Microservice.domain.Park.Park;
import park20.Park_Microservice.domain.Park.ParkInternalId;
import park20.Park_Microservice.dto.ParkWithSpotsAndParkyDTO;
import park20.Park_Microservice.dto.GetParkByIdDTO;
import park20.Park_Microservice.dto.Park2DTO;
import park20.Park_Microservice.dto.ParkWithDistanceDTO;
import park20.Park_Microservice.repository.ParkRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetParksService {

    ParkRepository parkRepository;

    public GetParksService(ParkRepository parkRepository) {
        this.parkRepository = parkRepository;
    }

    public List<ParkWithDistanceDTO> get() throws Exception {
        List<Park> parks = this.parkRepository.findAll();
        List<ParkWithDistanceDTO> parksDTO = new ArrayList<>();;
        for(Park p : parks){
            ParkWithDistanceDTO y = new ParkWithDistanceDTO();
            y.designation = p.getDesignation().toString();
            y.id = p.getId().toString();
            parksDTO.add(y);
        }
        return parksDTO;
    }

    public ParkWithSpotsAndParkyDTO getParkByID(String id) throws Exception {
        Park park = this.parkRepository.getParkById_Id(new ParkInternalId(id).toString());
        if (park == null) {
            throw new Exception("ParkNotExist");
        }
        return park.toDTOCoins();
    }
}
