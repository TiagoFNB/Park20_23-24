package park20.Park_Microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import park20.Park_Microservice.domain.Park.Park;
import park20.Park_Microservice.dto.*;
import park20.Park_Microservice.repository.ParkRepository;
import park20.Park_Microservice.shared.Utils;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetParksParkManagerService {

    protected RabbitTemplate rabbitTemplate;
    protected ParkRepository parkRepository;


    public GetParksParkManagerService(RabbitTemplate rabbitTemplate,ParkRepository parkRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.parkRepository = parkRepository;
    }

    public List<ParkWithDistanceDTO> get() throws Exception {
        GetParksManagerDTO dto=this.getParks();
        List<ParkWithDistanceDTO> parks = new ArrayList<>();;
        for(String id : dto.parkIds){
            Park x = this.parkRepository.getParkById_Id(id);
            ParkWithDistanceDTO y = new ParkWithDistanceDTO();
            y.designation = x.getDesignation().toString();
            parks.add(y);
        }

        return parks;
    }

    private GetParksManagerDTO getParks() throws Exception {
        GetManagerByIdDTO requestDTO = new GetManagerByIdDTO();
        requestDTO.id = Utils.getUserDetails().getId();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(requestDTO);

        jsonString = (String) rabbitTemplate.convertSendAndReceive("parks-manager-queue",jsonString);

        GetParksManagerDTO responseDto = objectMapper.readValue(jsonString, GetParksManagerDTO.class);
        return responseDto;
    }
}
