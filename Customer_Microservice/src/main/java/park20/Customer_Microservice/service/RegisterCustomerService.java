package park20.Customer_Microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import park20.Customer_Microservice.repository.CustomerRepository;
import park20.Customer_Microservice.domain.Customer.*;
import park20.Customer_Microservice.domain.Vehicle.Vehicle;
import park20.Customer_Microservice.dto.AuthResponseDTO;
import park20.Customer_Microservice.dto.CustomerCredentialsDTO;
import park20.Customer_Microservice.dto.CustomerDTO;
import park20.Customer_Microservice.shared.Utils;

import java.util.List;


@Service
public class RegisterCustomerService {

    protected RabbitTemplate rabbitTemplate;

    protected CustomerRepository customerRepository;
    protected Integer numberOfCoinsForRegistry=10;

    public RegisterCustomerService(RabbitTemplate rabbitTemplate, CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public AuthResponseDTO register(CustomerDTO customerDto) throws Exception {

        //Crate and register customer
        CustomerInternalId id = CustomerInternalId.genNewId();
        CustomerEmail email = new CustomerEmail(customerDto.email);
        CustomerUsername username = new CustomerUsername(customerDto.username);
        CustomerPassword password = new CustomerPassword(customerDto.password);
        CustomerName name = new CustomerName(customerDto.name);
        CustomerNif nif = new CustomerNif(customerDto.nif);

        Customer newCustomer = new Customer(id,email,password,username,name,nif,customerDto.handicapped);


        List<Vehicle> vehicles = Utils.extractVehicles(customerDto.vehicles);

        for(Vehicle v : vehicles) {
            newCustomer.addVehicle(v);
        }

        List<CustomerPaymentMethod> payments = Utils.extractPaymentMethod(customerDto.payments);

        for(CustomerPaymentMethod p : payments) {
            newCustomer.addPaymentMethod(p);
        }
        newCustomer.getWallet().addCoins(numberOfCoinsForRegistry);
        Customer savedCustomer = this.customerRepository.save(newCustomer);


        //Send rabbitmq request to login the newly registered customer
        CustomerCredentialsDTO requestDTO = new CustomerCredentialsDTO();
        requestDTO.username = savedCustomer.getUsername().toString();
        requestDTO.password = savedCustomer.getPass().toString();
        requestDTO.id = savedCustomer.getId().toString();


        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(requestDTO);

        jsonString = (String) rabbitTemplate.convertSendAndReceive("rabbit-postregister-queue",jsonString);

        AuthResponseDTO responseDto = objectMapper.readValue(jsonString, AuthResponseDTO.class);
        return responseDto;
    }
}
