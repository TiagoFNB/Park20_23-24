package park20.Manager_Microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import park20.Manager_Microservice.domain.Manager.Manager;
import park20.Manager_Microservice.domain.Manager.ManagerInternalId;
import park20.Manager_Microservice.dto.GetParkByIdDTO;
import park20.Manager_Microservice.dto.Park2DTO;
import park20.Manager_Microservice.repository.ManagerRepository;

@Service
public class AddParksToManagerService {

    RabbitTemplate rabbitTemplate;

    ManagerRepository managerRepository;

    public AddParksToManagerService(RabbitTemplate rabbitTemplate, ManagerRepository managerRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.managerRepository = managerRepository;
    }

    public void add(String managerId, String[] parkIds) throws Exception{

        Manager m = this.managerRepository.getManagerById(new ManagerInternalId(managerId));

        m.emptyParks();

        //Validate each park
        for(String p : parkIds) {
            Park2DTO dto = this.validateParkExists(p);
            if(dto.error != null) {
                throw(new Exception(dto.error));
            }
            m.addPark(p);
        }

        this.managerRepository.save(m);
    }


    private Park2DTO validateParkExists(String parkId) throws Exception {
        GetParkByIdDTO requestDTO = new GetParkByIdDTO();
        requestDTO.id = parkId;

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(requestDTO);

        jsonString = (String) rabbitTemplate.convertSendAndReceive("park-byId-queue",jsonString);

        Park2DTO responseDto = objectMapper.readValue(jsonString, Park2DTO.class);
        return responseDto;
    }
}
