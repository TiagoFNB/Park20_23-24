package park20.Customer_Microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import park20.Customer_Microservice.domain.Vehicle.VehicleCategoryEnum;
import park20.Customer_Microservice.dto.CheckParkAndReserveSlotDTO;
import park20.Customer_Microservice.dto.CheckParkAndReserveSlotResponseDTO;
import park20.Customer_Microservice.dto.DisplayTextDTO;
import park20.Customer_Microservice.repository.VehicleRepository;
import park20.Customer_Microservice.domain.Customer.Customer;
import park20.Customer_Microservice.domain.Vehicle.Vehicle;
import park20.Customer_Microservice.domain.Vehicle.VehicleLicensePlate;
import park20.Customer_Microservice.dto.VehicleParkTransitionDTO;
import park20.Customer_Microservice.service.Stay.RegisterStayService;
import park20.Customer_Microservice.shared.Utils;

@Service
public class VehicleEntryService {

    protected RabbitTemplate rabbitTemplate;
    protected VehicleRepository vehicleRepository;
    protected RegisterStayService stayService;

    private String displayUrl = "http://localhost:9012/display";
    private String gateOpenUrl = "http://localhost:9013/gate/open";

    protected final WebClient webClientDisplay;
    protected final WebClient webClientGateOpen;

    public VehicleEntryService(RabbitTemplate rabbitTemplate, VehicleRepository vehicleRepository, RegisterStayService stayService, WebClient.Builder webClientBuilder) {
        this.rabbitTemplate = rabbitTemplate;
        this.vehicleRepository = vehicleRepository;
        this.stayService = stayService;
        this.webClientDisplay = webClientBuilder.baseUrl(displayUrl).build();
        this.webClientGateOpen = webClientBuilder.baseUrl(gateOpenUrl).build();
    }

    public Object enter(VehicleParkTransitionDTO vehicleParkTransitionDTO) {
        try{
            VehicleLicensePlate plate = new VehicleLicensePlate(vehicleParkTransitionDTO.licensePlate);
            Vehicle v = this.vehicleRepository.getVehicleByPlate(plate);
            Customer c = v.getCustomer();
            CheckParkAndReserveSlotResponseDTO res = getParkDetails(v,c,vehicleParkTransitionDTO.idPark);
            if(res.freeSlot){

                this.stayService.register(v,vehicleParkTransitionDTO.idPark,res.slotId);
                Utils.display(webClientDisplay,res.welcomeMessage.replace("?1",c.getName().toString()));
                Utils.gate(webClientGateOpen);
            }else{
                String message= "No spots available for this type of vehicle!\nNão há lugares disponíveis para este tipo de veículo!";
                Utils.display(webClientDisplay,message);
            }

        } catch(Exception e) {
            String text = e.getMessage();
            String message= "No customer associated with vehicle!\nEste veículo não tem um cliente associado!";
            Utils.display(webClientDisplay,message);
        }

        return null;
    }

    private CheckParkAndReserveSlotResponseDTO getParkDetails(Vehicle v, Customer c, String designation) throws Exception{
        //Send rabbitmq request to park to get details
        CheckParkAndReserveSlotDTO requestDTO = new CheckParkAndReserveSlotDTO();
        requestDTO.designation = designation;
        requestDTO.category = c.getHandicapped() ? VehicleCategoryEnum.Handicapped.toString() :v.getCategory().toString();
        requestDTO.lang = c.getLanguage().toString();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(requestDTO);

        jsonString = (String) rabbitTemplate.convertSendAndReceive("rabbit-check-park-queue",jsonString);

        CheckParkAndReserveSlotResponseDTO responseDto = objectMapper.readValue(jsonString, CheckParkAndReserveSlotResponseDTO.class);
        return responseDto;
    }


}
