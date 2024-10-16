package park20.Park_Microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import park20.Park_Microservice.domain.Park.Park;
import park20.Park_Microservice.domain.Park.ParkSpot;
import park20.Park_Microservice.dto.CheckParkAndReserveSlotDTO;
import park20.Park_Microservice.dto.CheckParkAndReserveSlotResponseDTO;
import park20.Park_Microservice.dto.OpenSlotDTO;
import park20.Park_Microservice.dto.OpenSlotResponseDTO;
import park20.Park_Microservice.repository.ParkRepository;
import park20.Park_Microservice.repository.ParkSpotRepository;

import java.util.List;


@Service
public class OpenSlotService {

    private ParkRepository parkRepository;
    private ParkSpotRepository parkSpotRepository;

    public OpenSlotService(ParkRepository parkRepository, ParkSpotRepository parkSpotRepository) {
        this.parkRepository = parkRepository;
        this.parkSpotRepository = parkSpotRepository;
    }

    @RabbitListener(queues = "rabbit-open-slot-queue")
    private String rabbitOpenSlot(String jsonString) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            OpenSlotDTO dto = objectMapper.readValue(jsonString, OpenSlotDTO.class);
            ParkSpot ps = parkSpotRepository.getParkSpotById_Id(dto.slotId);
            ps.openSlot();
            Park p = parkRepository.getParkByDesignation_Designation(dto.parkId);
            parkSpotRepository.save(ps);
            OpenSlotResponseDTO res = new OpenSlotResponseDTO();
            res.slotId = ps.getId().toString();
            res.leavingMessage = getLeavingMessage(dto.lang,p);
            jsonString = objectMapper.writeValueAsString(res);
            return jsonString;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return jsonString;
    }

    private String getLeavingMessage(String lang, Park p){
        switch(lang){
            case "pt":
                return p.getLeavingMessage().getLeavingPortuguese();
            case "es":
                return p.getLeavingMessage().getLeavingSpanish();
            default:
                return p.getLeavingMessage().getLeavingEnglish();
        }
    }
}