package park20.Customer_Microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import park20.Customer_Microservice.repository.CustomerRepository;
import park20.Customer_Microservice.domain.Customer.Customer;
import park20.Customer_Microservice.domain.Customer.CustomerUsername;
import park20.Customer_Microservice.dto.CustomerCredentialsDTO;
import park20.Customer_Microservice.dto.GetCustomerByUsernameDTO;

@Service
public class GetCustomerCredentialsByUsernameService {

    protected CustomerRepository customerRepository;


    public GetCustomerCredentialsByUsernameService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RabbitListener(queues = "customer-queue")
    public String get(String jsonString) throws Exception {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            GetCustomerByUsernameDTO getCustomerByUsernameDTO = objectMapper.readValue(jsonString, GetCustomerByUsernameDTO.class);

            Customer c =  this.customerRepository.getCustomerByUsername(new CustomerUsername(getCustomerByUsernameDTO.username));

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
