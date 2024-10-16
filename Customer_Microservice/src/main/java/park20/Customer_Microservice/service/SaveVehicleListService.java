package park20.Customer_Microservice.service;

import org.springframework.stereotype.Service;

import park20.Customer_Microservice.domain.Customer.Customer;
import park20.Customer_Microservice.domain.Customer.CustomerInternalId;
import park20.Customer_Microservice.domain.Vehicle.Vehicle;
import park20.Customer_Microservice.dto.VehicleListDTO;
import park20.Customer_Microservice.repository.CustomerRepository;
import park20.Customer_Microservice.shared.Utils;

import java.util.List;

@Service
public class SaveVehicleListService {

    protected CustomerRepository customerRepository;

    public SaveVehicleListService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void save(VehicleListDTO vehiclesDto) throws Exception {

        //Obtain customer
        CustomerInternalId customerId = new CustomerInternalId(Utils.getUserDetails().getId());
        Customer customer = this.customerRepository.getCustomerById(customerId);

        // Extract vehicle data from dtos
        List<Vehicle> vehicles = Utils.extractVehicles(vehiclesDto.vehicles);

        customer.setVehicles(vehicles);

        // Saves customer
        this.customerRepository.save(customer);

        return;
    }
}
