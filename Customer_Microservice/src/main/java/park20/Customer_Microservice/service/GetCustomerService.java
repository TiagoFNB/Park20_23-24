package park20.Customer_Microservice.service;

import org.springframework.stereotype.Service;
import park20.Customer_Microservice.repository.CustomerRepository;
import park20.Customer_Microservice.domain.Customer.Customer;
import park20.Customer_Microservice.domain.Customer.CustomerInternalId;
import park20.Customer_Microservice.dto.CustomerDTO;

@Service
public class GetCustomerService {

    CustomerRepository customerRepository;

    public GetCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDTO get(CustomerInternalId id) throws Exception {
        Customer c = this.customerRepository.getCustomerById(id);
        if(c == null) {
            throw new Exception("CustomerNotExist");
        }

        return c.toDto();
    }
}
