package park20.Park_Microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import park20.Park_Microservice.domain.Park.ParkSpot;
import park20.Park_Microservice.repository.ParkRepository;
import park20.Park_Microservice.domain.Park.Park;
import park20.Park_Microservice.domain.Vehicle.VehicleCategory;
import park20.Park_Microservice.domain.Vehicle.VehicleCategoryEnum;
import park20.Park_Microservice.dto.CheckParkAndReserveSlotDTO;
import park20.Park_Microservice.dto.CheckParkAndReserveSlotResponseDTO;
import park20.Park_Microservice.repository.ParkSpotRepository;

import java.util.List;

import static java.util.Objects.isNull;


@Service
public class CheckParkAndReserveSlotService {

    private ParkRepository parkRepository;
    private ParkSpotRepository parkSpotRepository;

    public CheckParkAndReserveSlotService(ParkRepository parkRepository, ParkSpotRepository parkSpotRepository) {
        this.parkRepository = parkRepository;
        this.parkSpotRepository = parkSpotRepository;
    }

    @RabbitListener(queues = "rabbit-check-park-queue")
    private String rabbitCheckParkAndReserveSlotLogin(String jsonString) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            CheckParkAndReserveSlotDTO dto = objectMapper.readValue(jsonString, CheckParkAndReserveSlotDTO.class);
            Park p = parkRepository.getParkByDesignation_Designation(dto.designation);

            CheckParkAndReserveSlotResponseDTO res = new CheckParkAndReserveSlotResponseDTO();
            res.parkId = p.getId().toString();
            List<ParkSpot> psL = parkSpotRepository.findEmptyParkSlotForVehicleCategory(res.parkId,dto.category);
            if(!psL.isEmpty()) {
                ParkSpot ps = psL.get(0);
                ps.reserveSlot();
                parkSpotRepository.save(ps);
                res.welcomeMessage = getWelcomeMessage(dto.lang,p);
                res.slotId = ps.getId().toString();
                res.freeSlot = true;
            }else{
                res.freeSlot = false;
            }
            jsonString = objectMapper.writeValueAsString(res);
            return jsonString;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return jsonString;
    }

    private String getWelcomeMessage(String lang, Park p){
        switch(lang){
            case "pt":
                return p.getWelcomeMessage().getWelcomePortuguese();
            case "es":
                return p.getWelcomeMessage().getWelcomeSpanish();
            default:
                return p.getWelcomeMessage().getWelcomeEnglish();
        }
    }
}