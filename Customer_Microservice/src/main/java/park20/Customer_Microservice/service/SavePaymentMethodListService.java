package park20.Customer_Microservice.service;

import org.springframework.stereotype.Service;
import park20.Customer_Microservice.repository.CustomerRepository;
import park20.Customer_Microservice.domain.Customer.CustomerPaymentMethod;
import park20.Customer_Microservice.domain.Customer.Customer;
import park20.Customer_Microservice.domain.Customer.CustomerInternalId;
import park20.Customer_Microservice.dto.PaymentMethodListDTO;
import park20.Customer_Microservice.shared.Utils;

import java.util.List;

@Service
public class SavePaymentMethodListService {

    protected CustomerRepository customerRepository;

    public SavePaymentMethodListService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void save(PaymentMethodListDTO paymentsDto) throws Exception {

        //Obtain customer
        CustomerInternalId customerId = new CustomerInternalId(Utils.getUserDetails().getId());
        Customer customer = this.customerRepository.getCustomerById(customerId);

        // Extract vehicle data from dtos
        List<CustomerPaymentMethod> payments = Utils.extractPaymentMethod(paymentsDto.methods);

        customer.setPaymentMethods(payments);

        // Saves customer
        this.customerRepository.save(customer);

        return;
    }
}
