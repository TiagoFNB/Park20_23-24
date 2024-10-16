package park20.Authentication_Microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import park20.Authentication_Microservice.dto.AuthDTO;
import park20.Authentication_Microservice.dto.AuthResponseDTO;
import park20.Authentication_Microservice.dto.CustomerCredentialsDTO;
import park20.Authentication_Microservice.shared.JWTGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class LoginServiceTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private JWTGenerator jWTGenerator;

    @InjectMocks
    private LoginService loginService;

    @Test
    void testLogin_ValidCredentials() throws Exception {
        // Setup
        AuthDTO authDTO = new AuthDTO();
        authDTO.username= "testUser";
        authDTO.password="testPassword";

        CustomerCredentialsDTO credentialsDTO = new CustomerCredentialsDTO();
        credentialsDTO.username = "testUser";
        credentialsDTO.password = "testPassword";
        credentialsDTO.id = "testId";

        AuthResponseDTO expectedResponse = new AuthResponseDTO();
        expectedResponse.token = "testToken";

        Mockito.when(rabbitTemplate.convertSendAndReceive(anyString(), anyString())).thenReturn(new ObjectMapper().writeValueAsString(credentialsDTO));
        Mockito.when(jWTGenerator.generateToken(anyString(), anyString(), Mockito.eq(true))).thenReturn("testToken");

        // Call
        AuthResponseDTO actualResponse = loginService.login(authDTO);

        Mockito.verify(jWTGenerator, Mockito.times(1)).generateToken("testUser", "testId", true);

        // Assert
        assertEquals(expectedResponse.token, actualResponse.token);
    }

    @Test
    void testLogin_InvalidCredentials() throws Exception {
        // Setup
        AuthDTO authDTO = new AuthDTO();
        authDTO.username= "testUser";
        authDTO.password="testPasswordError";

        CustomerCredentialsDTO credentialsDTO = new CustomerCredentialsDTO();
        credentialsDTO.username = "testUser";
        credentialsDTO.password = "testPassword";

        AuthResponseDTO expectedResponse = new AuthResponseDTO();
        expectedResponse.token = "testToken";

        Mockito.when(rabbitTemplate.convertSendAndReceive(anyString(), anyString())).thenReturn(new ObjectMapper().writeValueAsString(credentialsDTO));
        Mockito.when(jWTGenerator.generateToken(anyString(), anyString(), Mockito.eq(true))).thenReturn("testToken");

        // Call
        Exception exception = assertThrows(Exception.class, () -> {
            loginService.login(authDTO);
        });
    }

    @Test
    void testLogin_ValidCredentialsManager() throws Exception {
        // Setup
        AuthDTO authDTO = new AuthDTO();
        authDTO.username= "testUser";
        authDTO.password="testPassword";

        CustomerCredentialsDTO credentialsDTO = new CustomerCredentialsDTO();
        credentialsDTO.username = "testUser";
        credentialsDTO.password = "testPassword";
        credentialsDTO.id = "testId";

        AuthResponseDTO expectedResponse = new AuthResponseDTO();
        expectedResponse.token = "testToken";

        Mockito.when(rabbitTemplate.convertSendAndReceive(anyString(), anyString())).thenReturn(new ObjectMapper().writeValueAsString(credentialsDTO));
        Mockito.when(jWTGenerator.generateToken(anyString(), anyString(), Mockito.eq(false))).thenReturn("testToken");

        // Call
        AuthResponseDTO actualResponse = loginService.loginManager(authDTO);

        Mockito.verify(jWTGenerator, Mockito.times(1)).generateToken("testUser", "testId", false);

        // Assert
        assertEquals(expectedResponse.token, actualResponse.token);
    }

    @Test
    void testLogin_InvalidCredentialsManager() throws Exception {
        // Setup
        AuthDTO authDTO = new AuthDTO();
        authDTO.username= "testUser";
        authDTO.password="testPasswordError";

        CustomerCredentialsDTO credentialsDTO = new CustomerCredentialsDTO();
        credentialsDTO.username = "testUser";
        credentialsDTO.password = "testPassword";

        AuthResponseDTO expectedResponse = new AuthResponseDTO();
        expectedResponse.token = "testToken";

        Mockito.when(rabbitTemplate.convertSendAndReceive(anyString(), anyString())).thenReturn(new ObjectMapper().writeValueAsString(credentialsDTO));
        Mockito.when(jWTGenerator.generateToken(anyString(), anyString(), Mockito.eq(true))).thenReturn("testToken");

        // Call
        Exception exception = assertThrows(Exception.class, () -> {
            loginService.loginManager(authDTO);
        });
    }
}