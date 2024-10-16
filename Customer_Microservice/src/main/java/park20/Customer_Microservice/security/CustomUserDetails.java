package park20.Customer_Microservice.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {

    private String id;

    private Boolean isCustomer;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String id, Boolean isCustomer) {
        super(username, password, authorities);

        this.id = id;
        this.isCustomer = isCustomer;
    }

    public String getId() {
        return id;
    }

    public Boolean getIsCustomer() {
        return isCustomer;
    }
}
