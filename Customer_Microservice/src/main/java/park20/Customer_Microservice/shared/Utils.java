package park20.Customer_Microservice.shared;

import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import park20.Customer_Microservice.domain.Vehicle.*;
import park20.Customer_Microservice.domain.Customer.CustomerPaymentMethod;
import park20.Customer_Microservice.domain.Customer.PaymentMethodInternalId;
import park20.Customer_Microservice.dto.CustomerPaymentMethodDTO;
import park20.Customer_Microservice.dto.DisplayTextDTO;
import park20.Customer_Microservice.dto.VehicleDTO;
import park20.Customer_Microservice.security.CustomUserDetails;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static CustomUserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return userDetails;
        }

        return null;
    }

    public static List<Vehicle> extractVehicles(VehicleDTO[] vehiclesDTO) throws Exception {
        if(vehiclesDTO.length == 0) {
            throw new Exception("CustomerNeedsVehicles");
        }

        List<Vehicle> vehicles = new ArrayList<>();

        for(VehicleDTO dto : vehiclesDTO) {
            VehicleInternalId id = VehicleInternalId.genNewId();
            VehicleLicensePlate plate = new VehicleLicensePlate(dto.plate);
            VehicleBrand brand = new VehicleBrand(dto.brand);
            VehicleModel model = new VehicleModel(dto.model);
            VehicleCategory category = new VehicleCategory(VehicleCategoryEnum.valueOf(dto.category));

            Vehicle v = new Vehicle(id,plate,brand,model,category);

            vehicles.add(v);
        }

        return vehicles;
    }

    public static List<CustomerPaymentMethod> extractPaymentMethod(CustomerPaymentMethodDTO[] paymentsDTO) throws Exception {
        if(paymentsDTO.length == 0) {
            throw new Exception("CustomerNeedsPaymentMethods");
        }

        List<CustomerPaymentMethod> paymentsM = new ArrayList<>();

        for(CustomerPaymentMethodDTO dto : paymentsDTO) {
            PaymentMethodInternalId id = PaymentMethodInternalId.genNewId();
            CustomerPaymentMethod phone = new CustomerPaymentMethod(dto.phoneNumber, id);

            paymentsM.add(phone);
        }

        return paymentsM;
    }

    public static String HumanReadableConstraintViolation(DataIntegrityViolationException exception) {

        PSQLException rootCause = (PSQLException) exception.getRootCause();

        ServerErrorMessage err = rootCause.getServerErrorMessage();

        String table = err.getTable();
        String cause = err.getRoutine();

        table = Character.toUpperCase(table.charAt(0)) + table.substring(1);

        if(cause.contains("unique")) {
            cause = "Unique";
        }

        return table+cause+"Violation";
    }

    public static Object display(WebClient webClientDisplay, String message) {

        DisplayTextDTO sendDTO = new DisplayTextDTO();
        sendDTO.text = message;

        return webClientDisplay.post()
                .body(BodyInserters.fromValue(sendDTO))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public static Object gate(WebClient webClientGate) {

        return webClientGate.post()
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
