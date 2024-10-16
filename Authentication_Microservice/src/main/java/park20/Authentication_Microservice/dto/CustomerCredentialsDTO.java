package park20.Authentication_Microservice.dto;

import java.io.Serializable;

public class CustomerCredentialsDTO implements Serializable {

    public String id;
    public String username;
    public String password;
    public String error;
}
