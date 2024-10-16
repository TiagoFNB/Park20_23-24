package park20.Customer_Microservice.service;

import org.springframework.stereotype.Service;

import park20.Customer_Microservice.domain.Customer.Customer;
import park20.Customer_Microservice.domain.Stay.Stay;
import park20.Customer_Microservice.domain.Vehicle.Vehicle;
import park20.Customer_Microservice.dto.RankCustomerUsageDTO;
import park20.Customer_Microservice.repository.CustomerRepository;
import park20.Customer_Microservice.repository.StayRepository;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RankCustomersParkUsageService {

    protected CustomerRepository customerRepository;
    protected StayRepository stayRepository;

    public RankCustomersParkUsageService(CustomerRepository customerRepository,StayRepository stayRepository) {
        this.customerRepository = customerRepository;
        this.stayRepository = stayRepository;
    }

    public List<RankCustomerUsageDTO> getList(String[] parkIds){

        List<Customer> customerList = this.customerRepository.findAll();
        List<RankCustomerUsageDTO> listDTO = new ArrayList<>();
        for(Customer c: customerList){
            long totalDuration = 0;
            for(Vehicle v: c.getVehicles()){
                List<Stay> staysVehicle = this.stayRepository.getStaysByVehicleAndStayEndTimeIsNotNull(v);
                for(Stay s: staysVehicle){
                    long minutes = ChronoUnit.MINUTES.between(s.getStayStartTime().toDate(), s.getStayEndTime().toDate());
                    totalDuration += minutes;
                }
            }
            RankCustomerUsageDTO dto = new RankCustomerUsageDTO();
            dto.id = c.getId().toString();
            dto.duration = totalDuration;
            dto.name = c.getName().toString();
            dto.email = c.getEmail().toString();
            listDTO.add(dto);
        }
        listDTO.sort((o1, o2) -> Long.signum(o2.duration - o1.duration));


        return listDTO ;
    }



}
