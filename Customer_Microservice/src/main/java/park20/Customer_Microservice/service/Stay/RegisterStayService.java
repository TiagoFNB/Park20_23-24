package park20.Customer_Microservice.service.Stay;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import park20.Customer_Microservice.domain.Stay.*;
import park20.Customer_Microservice.domain.Vehicle.Vehicle;
import park20.Customer_Microservice.dto.CheckParkAndReserveSlotDTO;
import park20.Customer_Microservice.dto.CheckParkAndReserveSlotResponseDTO;
import park20.Customer_Microservice.repository.StayRepository;
import park20.Customer_Microservice.repository.VehicleRepository;

import java.time.LocalDateTime;


@Service
public class RegisterStayService {

    protected RabbitTemplate rabbitTemplate;

    protected StayRepository stayRepository;
    protected VehicleRepository vehicleRepository;

    public RegisterStayService(RabbitTemplate rabbitTemplate, StayRepository stayRepository, VehicleRepository vehicleRepository) {
        this.stayRepository = stayRepository;
        this.vehicleRepository = vehicleRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Stay register(Vehicle v, String parkId, String slotId) throws Exception {

            //Create and register stay
            StayInternalId id = StayInternalId.genNewId();
            StayStartTime stayStartTime = new StayStartTime(LocalDateTime.now());


            StayParkId stayParkId = new StayParkId(parkId);
            StaySlotId staySlotId = new StaySlotId(slotId);

            Stay newStay = new Stay(id, stayStartTime, stayParkId, v,staySlotId);

            Stay savedStay = this.stayRepository.save(newStay);

            return savedStay;
    }


}
