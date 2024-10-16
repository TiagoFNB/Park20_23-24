package park20.Customer_Microservice.service;

import org.springframework.stereotype.Service;
import park20.Customer_Microservice.dto.AssignCoinsDTO;
import park20.Customer_Microservice.repository.CustomerRepository;

@Service
public class BulkAssignParkyCoinsService {

        protected CustomerRepository customerRepository;

        public BulkAssignParkyCoinsService(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        public void assign(AssignCoinsDTO dto) {
            this.customerRepository.updateAmountForAllCustomers(dto.amount);
        }
}
