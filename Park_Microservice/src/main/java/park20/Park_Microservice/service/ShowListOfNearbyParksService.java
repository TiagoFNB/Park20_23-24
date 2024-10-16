package park20.Park_Microservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import park20.Park_Microservice.repository.ParkRepository;
import park20.Park_Microservice.domain.Park.Park;
import park20.Park_Microservice.domain.Park.ParkSpot;
import park20.Park_Microservice.domain.Vehicle.VehicleCategory;
import park20.Park_Microservice.domain.Vehicle.VehicleCategoryEnum;
import park20.Park_Microservice.dto.ParkAddressDTO;
import park20.Park_Microservice.dto.ParkWithDistanceDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowListOfNearbyParksService {

    private final ParkRepository parkRepository;

    public ShowListOfNearbyParksService(ParkRepository parkRepository) {
        this.parkRepository = parkRepository;
    }

    public List<ParkWithDistanceDTO> getListWithDistance(String userLatitude, String userLongitude){

        List<Park> parks = parkRepository.findAll();
        List<ParkWithDistanceDTO> parksWithDistance = new ArrayList<>();
        for(Park p : parks){
            ParkWithDistanceDTO park = new ParkWithDistanceDTO();
            double latitude = Double.parseDouble(p.getAddress().getLatitude());
            double longitude = Double.parseDouble(p.getAddress().getLongitude());

            double distance = calculateDistance(Double.parseDouble(userLatitude),Double.parseDouble(userLongitude), latitude,longitude);


            park.distance = Math.round((distance) * 100) / 100;
            park.designation = p.getDesignation().toString();

            park.address = new ParkAddressDTO();
            park.address.city = p.getAddress().getCity();
            park.address.street = p.getAddress().getStreet();
            park.address.postalCode = p.getAddress().getPostalCode();
            park.address.latitude = p.getAddress().getLatitude();
            park.address.longitude = p.getAddress().getLongitude();

            park.spotEletric = (int) countSpotsByTypeAndNotOccupied(p.getParkSpots(), new VehicleCategory(VehicleCategoryEnum.Eletric));
            park.spotGas = (int) countSpotsByTypeAndNotOccupied(p.getParkSpots(), new VehicleCategory(VehicleCategoryEnum.Gas));
            park.spotGPL = (int) countSpotsByTypeAndNotOccupied(p.getParkSpots(), new VehicleCategory(VehicleCategoryEnum.GPL));
            park.spotHandicap = (int) countSpotsByTypeAndNotOccupied(p.getParkSpots(), new VehicleCategory(VehicleCategoryEnum.Handicapped));
            park.spotMotorcycle = (int) countSpotsByTypeAndNotOccupied(p.getParkSpots(), new VehicleCategory(VehicleCategoryEnum.Motorcycle));

            parksWithDistance.add(park);

        }

        return parksWithDistance;
    }

    public long countSpotsByTypeAndNotOccupied(List<ParkSpot> parkSpots, VehicleCategory targetType) {

        return parkSpots.stream().filter(parkSpot -> parkSpot.isOcupied() == false).filter(parkSpot -> parkSpot.getType().equals(targetType)).count();
    }






    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the Earth in kilometers

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // Distance in kilometers
    }

}
