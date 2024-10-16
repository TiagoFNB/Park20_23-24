package park20.Manager_Microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import park20.Manager_Microservice.domain.Manager.Manager;
import park20.Manager_Microservice.domain.Manager.ManagerInternalId;
import park20.Manager_Microservice.domain.Manager.ManagerUsername;
import park20.Manager_Microservice.dto.GetManagerByIdDTO;
import park20.Manager_Microservice.dto.GetParksManagerDTO;
import park20.Manager_Microservice.repository.ManagerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetParksByManagerService {

    ManagerRepository managerRepository;

    public GetParksByManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public List<String> get(String user) throws Exception {
        ManagerUsername username = new ManagerUsername(user);
        Manager m = this.managerRepository.getManagerByUsername(username);
        return m.getParks();
    }

    @RabbitListener(queues = "parks-manager-queue")
    public String getRabbit(String jsonString) throws Exception {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            GetManagerByIdDTO getManagerByIdDTO = objectMapper.readValue(jsonString, GetManagerByIdDTO.class);

            Manager m = this.managerRepository.getManagerById(new ManagerInternalId(getManagerByIdDTO.id));


            GetParksManagerDTO dto = new GetParksManagerDTO();
            dto.parkIds = new ArrayList<>();
            if(m == null) {
                dto.error = "ManagerNotFound";
            } else {
                dto.parkIds.addAll(m.getParks());
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
