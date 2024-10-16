package park20.Customer_Microservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import park20.Customer_Microservice.domain.Stay.Stay;
import park20.Customer_Microservice.domain.Vehicle.VehicleCategoryEnum;
import park20.Customer_Microservice.dto.*;
import park20.Customer_Microservice.repository.VehicleRepository;
import park20.Customer_Microservice.domain.Customer.Customer;
import park20.Customer_Microservice.domain.Vehicle.Vehicle;
import park20.Customer_Microservice.domain.Vehicle.VehicleLicensePlate;
import park20.Customer_Microservice.service.Stay.EndStayService;
import park20.Customer_Microservice.service.Stay.RegisterStayService;
import park20.Customer_Microservice.shared.Utils;

@Service
public class VehicleExitService {

    protected RabbitTemplate rabbitTemplate;
    protected VehicleRepository vehicleRepository;
    protected EndStayService stayService;

    private String displayUrl = "http://localhost:9015/display";
    private String gateOpenUrl = "http://localhost:9014/gate/open";

    //! Temporary
    private String parkUrl = "http://localhost:9002/emit-payment";

    protected final WebClient webClientDisplay;
    protected final WebClient webClientGateClose;
    protected final WebClient webClientEmitPayment;

    public VehicleExitService(RabbitTemplate rabbitTemplate, VehicleRepository vehicleRepository, EndStayService stayService, WebClient.Builder webClientBuilder) {
        this.rabbitTemplate = rabbitTemplate;
        this.vehicleRepository = vehicleRepository;
        this.stayService = stayService;
        this.webClientDisplay = webClientBuilder.baseUrl(displayUrl).build();
        this.webClientGateClose = webClientBuilder.baseUrl(gateOpenUrl).build();
        this.webClientEmitPayment = webClientBuilder.baseUrl(parkUrl).build();
    }

    public Object exit(VehicleParkTransitionDTO vehicleParkTransitionDTO) {
        try{
            VehicleLicensePlate plate = new VehicleLicensePlate(vehicleParkTransitionDTO.licensePlate);
            Vehicle v = this.vehicleRepository.getVehicleByPlate(plate);
            Customer c = v.getCustomer();

            Stay s = this.stayService.endStay(v);
            OpenSlotResponseDTO res = openSlot(v,c,s.getStaySlotId().toString(),s.getStayParkId().toString());
            PaymentRequestDTO sendDTO = new PaymentRequestDTO();
            sendDTO.parkDesignation= s.getStayParkId().toString();
            sendDTO.stayStartTime = s.getStayStartTime().toString();
            sendDTO.stayEndTime = s.getStayEndTime().toString();
            sendDTO.category = v.getCategory().toString();
            String price = webClientEmitPayment.post().body(BodyInserters.fromValue(sendDTO))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            Utils.display(webClientDisplay,res.leavingMessage.replace("?1",c.getName().toString() + " - " + price + "€" ));
            Utils.gate(webClientGateClose);

            //TODO: PAGAMENTO

        } catch(Exception e) {
            String text = e.getMessage();
            String message= "No customer associated with vehicle!\nEste veículo não tem um cliente associado!";
            Utils.display(webClientDisplay,message);
        }

        return null;
    }

    private OpenSlotResponseDTO openSlot(Vehicle v, Customer c, String slotId, String parkId) throws Exception{
        //Send rabbitmq request to park to get details
        OpenSlotDTO requestDTO = new OpenSlotDTO();
        requestDTO.slotId = slotId;
        requestDTO.parkId = parkId;
        requestDTO.lang = c.getLanguage().toString();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(requestDTO);

        jsonString = (String) rabbitTemplate.convertSendAndReceive("rabbit-open-slot-queue",jsonString);

        OpenSlotResponseDTO responseDto = objectMapper.readValue(jsonString, OpenSlotResponseDTO.class);
        return responseDto;
    }
}