package park20.Park_Microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import park20.Park_Microservice.domain.Park.Park;
import park20.Park_Microservice.dto.GetParkByIdDTO;
import park20.Park_Microservice.dto.Park2DTO;
import park20.Park_Microservice.repository.ParkRepository;

@Service
public class GetParkService {

    ParkRepository parkRepository;

    public GetParkService(ParkRepository parkRepository) {
        this.parkRepository = parkRepository;
    }

    public Park get(String id) throws Exception {
        Park p = this.parkRepository.getParkById_Id(id);
        return p;
    }

    @RabbitListener(queues = "park-byId-queue")
    public String getRabbit(String jsonString) throws Exception {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            GetParkByIdDTO getParkByIdDTO = objectMapper.readValue(jsonString, GetParkByIdDTO.class);

            Park p = this.get(getParkByIdDTO.id);


            Park2DTO dto = new Park2DTO();
            if(p == null) {
                dto.error = "ParkNotFound";
            } else {
                dto.id = p.getId().toString();
            }

            jsonString = objectMapper.writeValueAsString(dto);
            return jsonString;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return jsonString;
    }
}
