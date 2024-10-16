package park20.Customer_Microservice.service.Stay;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import park20.Customer_Microservice.domain.Stay.*;
import park20.Customer_Microservice.domain.Vehicle.Vehicle;
import park20.Customer_Microservice.repository.StayRepository;
import park20.Customer_Microservice.repository.VehicleRepository;

import java.time.LocalDateTime;


@Service
public class EndStayService {

    protected RabbitTemplate rabbitTemplate;

    protected StayRepository stayRepository;
    protected VehicleRepository vehicleRepository;

    public EndStayService(RabbitTemplate rabbitTemplate, StayRepository stayRepository, VehicleRepository vehicleRepository) {
        this.stayRepository = stayRepository;
        this.vehicleRepository = vehicleRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Stay endStay(Vehicle v) {

            //Obtain stay with null endtime
            Stay s = this.stayRepository.findTopByStayEndTimeNullAndVehicle(v);
            StayEndTime stayEndTimeTime = new StayEndTime(LocalDateTime.now());
            s.addStayEndTime(stayEndTimeTime);
            Stay savedStay = this.stayRepository.save(s);

            return savedStay;
    }


}
