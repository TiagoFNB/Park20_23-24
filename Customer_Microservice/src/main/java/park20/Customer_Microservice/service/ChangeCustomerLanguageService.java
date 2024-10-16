package park20.Customer_Microservice.service;

import org.springframework.stereotype.Service;
import park20.Customer_Microservice.repository.CustomerRepository;
import park20.Customer_Microservice.domain.Customer.Customer;
import park20.Customer_Microservice.domain.Customer.CustomerInternalId;
import park20.Customer_Microservice.domain.Customer.CustomerLanguagesEnum;
import park20.Customer_Microservice.dto.ChangeLanguageDTO;
import park20.Customer_Microservice.shared.Utils;

@Service
public class ChangeCustomerLanguageService {

    CustomerRepository customerRepository;

    public ChangeCustomerLanguageService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void change(ChangeLanguageDTO changeLanguageDTO) throws Exception {
        Customer c = this.customerRepository.getCustomerById(new CustomerInternalId(Utils.getUserDetails().getId()));
        if(c == null) {
            throw new Exception("CustomerNotExist");
        }

        c.changeLanguage(CustomerLanguagesEnum.valueOf(changeLanguageDTO.language));
        this.customerRepository.save(c);
    }
}
