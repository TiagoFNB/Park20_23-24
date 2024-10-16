package park20.Customer_Microservice.dto;

import park20.Customer_Microservice.domain.ParkyWallet.ParkyWallet;

import java.io.Serializable;

public class CustomerDTO implements Serializable {

    public String id;
    public String email;
    public String username;
    public String password;
    public String name;
    public String nif;
    public boolean handicapped;
    public CustomerPaymentMethodDTO[] payments;
    public VehicleDTO[] vehicles;
    public String language;

    public ParkyWalletDTO wallet;
}
