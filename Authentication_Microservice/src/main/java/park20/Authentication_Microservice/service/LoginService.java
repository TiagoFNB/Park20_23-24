package park20.Authentication_Microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import park20.Authentication_Microservice.dto.AuthDTO;
import park20.Authentication_Microservice.dto.AuthResponseDTO;
import park20.Authentication_Microservice.dto.CustomerCredentialsDTO;
import park20.Authentication_Microservice.dto.GetCustomerByUsernameDTO;
import park20.Authentication_Microservice.shared.JWTGenerator;

@Service
public class LoginService {

    private RabbitTemplate rabbitTemplate;

    private JWTGenerator jWTGenerator;

    public LoginService(RabbitTemplate rabbitTemplate, JWTGenerator jWTGenerator) {
        this.rabbitTemplate = rabbitTemplate;
        this.jWTGenerator = jWTGenerator;
    }

    @RabbitListener(queues = "rabbit-postregister-queue")
    private String rabbitPostRegisterLogin(String jsonString) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            CustomerCredentialsDTO credentialsDTO = objectMapper.readValue(jsonString, CustomerCredentialsDTO.class);

            AuthDTO authDTO = new AuthDTO();
            authDTO.username = credentialsDTO.username;
            authDTO.password = credentialsDTO.password;

            AuthResponseDTO res = this.validateCredentials(authDTO,credentialsDTO, true);

            jsonString = objectMapper.writeValueAsString(res);
            return jsonString;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return jsonString;
    }

    public AuthResponseDTO login(AuthDTO authDTO) throws Exception {
        CustomerCredentialsDTO credentialsDTO = requestCustomerCredentialsByEmail(authDTO.username);

        if(credentialsDTO.error == null) {
            return validateCredentials(authDTO, credentialsDTO, true);
        }
        throw new Exception(credentialsDTO.error);
    }

    public AuthResponseDTO loginManager(AuthDTO authDTO) throws Exception {
        CustomerCredentialsDTO credentialsDTO = requestManagerCredentialsByUsername(authDTO.username);

        if(credentialsDTO.error == null) {
            return validateCredentials(authDTO, credentialsDTO, false);
        }

        throw new Exception(credentialsDTO.error);
    }

    private AuthResponseDTO validateCredentials(AuthDTO authDTO,CustomerCredentialsDTO credentialsDTO, boolean isCustomer) throws Exception{
        if(!authDTO.password.equals(credentialsDTO.password)) {
            throw new Exception("InvalidPassword");
        }

        String token = jWTGenerator.generateToken(credentialsDTO.username, credentialsDTO.id, isCustomer);

        AuthResponseDTO res = new AuthResponseDTO();
        res.token = token;

        return res;
    }

    private CustomerCredentialsDTO requestCustomerCredentialsByEmail(String username) throws Exception {
        GetCustomerByUsernameDTO requestDTO = new GetCustomerByUsernameDTO();
        requestDTO.username = username;


        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(requestDTO);

        jsonString = (String) rabbitTemplate.convertSendAndReceive("customer-queue",jsonString);

        CustomerCredentialsDTO responseDto = objectMapper.readValue(jsonString, CustomerCredentialsDTO.class);
        return responseDto;
    }

    private CustomerCredentialsDTO requestManagerCredentialsByUsername(String username) throws Exception {
        GetCustomerByUsernameDTO requestDTO = new GetCustomerByUsernameDTO();
        requestDTO.username = username;


        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(requestDTO);

        jsonString = (String) rabbitTemplate.convertSendAndReceive("manager-queue",jsonString);

        CustomerCredentialsDTO responseDto = objectMapper.readValue(jsonString, CustomerCredentialsDTO.class);
        return responseDto;
    }
}
