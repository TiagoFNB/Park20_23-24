package park20.Authentication_Microservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import park20.Authentication_Microservice.dto.TokenDTO;
import park20.Authentication_Microservice.shared.JWTGenerator;

@Service
public class TokenValidationService {

    private JWTGenerator jWTGenerator;

    public TokenValidationService(JWTGenerator jWTGenerator) {
        this.jWTGenerator = jWTGenerator;
    }

    @RabbitListener(queues = "auth-queue")
    public String validateToken(String token) throws JsonProcessingException {
        TokenDTO tokenDto = new TokenDTO();
        tokenDto.token = token;

        try {
            // Validate the token using your JWTGenerator
            boolean isValid = this.jWTGenerator.validateToken(token);

            tokenDto.username = this.jWTGenerator.getUsernameFromToken(token);
            tokenDto.id = this.jWTGenerator.getIdFromToken(token);
            tokenDto.isCustomer = this.jWTGenerator.getIsCustomerFromToken(token);

            // Create a validation response
            if(isValid) tokenDto.valid = true;
        } catch (Exception e) {
            tokenDto.valid = false;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(tokenDto);
    }
}