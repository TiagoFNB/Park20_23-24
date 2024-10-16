package park20.Manager_Microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import park20.Manager_Microservice.repository.ManagerRepository;
import park20.Manager_Microservice.domain.Manager.Manager;
import park20.Manager_Microservice.domain.Manager.ManagerUsername;
import park20.Manager_Microservice.dto.CustomerCredentialsDTO;
import park20.Manager_Microservice.dto.GetManagerByUsernameDTO;

@Service
public class GetManagerCredentialsByUsernameService {

    protected ManagerRepository managerRepository;


    public GetManagerCredentialsByUsernameService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @RabbitListener(queues = "manager-queue")
    public String get(String jsonString) throws Exception {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            GetManagerByUsernameDTO getManagerByUsernameDTO = objectMapper.readValue(jsonString, GetManagerByUsernameDTO.class);

            Manager c =  this.managerRepository.getManagerByUsername(new ManagerUsername(getManagerByUsernameDTO.username));

            CustomerCredentialsDTO dto = new CustomerCredentialsDTO();
            if(c == null) {
                dto.error = "NotFound";
            } else {
                dto.id = c.getId().toString();
                dto.username = c.getUsername().toString();
                dto.password = c.getPass().toString();
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
