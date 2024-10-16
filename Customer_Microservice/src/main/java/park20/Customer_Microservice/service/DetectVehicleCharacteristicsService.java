package park20.Customer_Microservice.service;


import org.springframework.stereotype.Service;
import park20.Customer_Microservice.domain.Vehicle.VehicleCategoryEnum;
import park20.Customer_Microservice.dto.VehicleDTO;

import java.util.Random;


@Service
public class DetectVehicleCharacteristicsService {

    public DetectVehicleCharacteristicsService() {
    }

    public VehicleDTO detect(String licensePlate) {
        //Create a seed with the given licensePlate
        long seed = licensePlate.hashCode();
        Random random = new Random(seed);

        // Generate a random number between 1 and 10
        int randomNumber = random.nextInt(10) + 1;

        VehicleDTO dto = new VehicleDTO();
        dto.plate = licensePlate;

        switch (randomNumber) {
            case 1:
                dto.category = VehicleCategoryEnum.GPL.toString();
                dto.brand = "Honda";
                dto.model = "Civic";
                break;
            case 2:
                dto.category = VehicleCategoryEnum.GPL.toString();
                dto.brand = "Citroen";
                dto.model = "C4";
                break;
            case 3:
                dto.category = VehicleCategoryEnum.Eletric.toString();
                dto.brand = "Tesla";
                dto.model = "Model 3";
                break;
            case 4:
                dto.category = VehicleCategoryEnum.Eletric.toString();
                dto.brand = "Nissan";
                dto.model = "Leaf";
                break;
            case 5:
                dto.category = VehicleCategoryEnum.Eletric.toString();
                dto.brand = "Renault";
                dto.model = "Zoe";
                break;
            case 6:
                dto.category = VehicleCategoryEnum.Gas.toString();
                dto.brand = "Seat";
                dto.model = "Ibiza";
                break;
            case 7:
                dto.category = VehicleCategoryEnum.Gas.toString();
                dto.brand = "Renault";
                dto.model = "Clio";
                break;
            case 8:
                dto.category = VehicleCategoryEnum.Gas.toString();
                dto.brand = "Ford";
                dto.model = "Fiesta";
                break;
            case 9:
                dto.category = VehicleCategoryEnum.Motorcycle.toString();
                dto.brand = "Kawasaki";
                dto.model = "Ninja";
                break;
            case 10:
                dto.category = VehicleCategoryEnum.Motorcycle.toString();
                dto.brand = "Yamaha";
                dto.model = "MT07";
                break;
        }

        return dto;
    }
}
