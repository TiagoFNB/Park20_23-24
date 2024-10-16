package park20.Authentication_Microservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import park20.Authentication_Microservice.dto.AuthDTO;
import park20.Authentication_Microservice.dto.AuthResponseDTO;
import park20.Authentication_Microservice.service.LoginService;

@RestController
public class LoginManagerController {

    protected LoginService loginService;

    public LoginManagerController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = "/login-manager")
    public AuthResponseDTO login(@RequestBody AuthDTO authDTO) {
        try{
            return this.loginService.loginManager(authDTO);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
