package park20.Park_Microservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import park20.Park_Microservice.repository.ParkRepository;
import park20.Park_Microservice.domain.Park.*;
import park20.Park_Microservice.domain.Vehicle.VehicleCategory;
import park20.Park_Microservice.domain.Vehicle.VehicleCategoryEnum;

import java.util.ArrayList;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ParkRepository parkRepository;
    @Override
    public void run(String... args) throws Exception {
        if (parkRepository.count() == 0) {

            ParkWelcomeMessage welcomeMessage = new ParkWelcomeMessage("Bem-vindo ?1", "Welcome ?1","Bienvenido ?1" );
            ParkLeavingMessage leavingMessage = new ParkLeavingMessage("Obrigado ?1 !", "Thank you ?1 !", "¡Gracias ?1 !");


            ParkInternalId parkId1 = new ParkInternalId("idPark1");
            ParkAddress address1 = new ParkAddress("Rua 1","Porto", "4580-111", "41.177477", "-8.609351");
            Park park1 = new Park(parkId1,address1,genParkSpots(),0,0,0,0,0,0,0,0, welcomeMessage,leavingMessage,new ParkDesignation("PARK1"));

            ParkInternalId parkId2 = new ParkInternalId("idPark2");
            ParkAddress address2 = new ParkAddress("Rua 2","Porto", "4580-500", "41.196493", "-8.574718");
            Park park2 = new Park(parkId2,address2,genParkSpots(),0,0,0,0,0,0,0,0, welcomeMessage,leavingMessage,new ParkDesignation("PARK2"));

            ParkInternalId parkId3 = new ParkInternalId("idPark3");
            ParkAddress address3 = new ParkAddress("Rua 3","Porto", "4580-900", "41.196001", "-8.351960");
            Park park3 = new Park(parkId3,address3,genParkSpots(),0,0,0,0,0,0,0,0,welcomeMessage,leavingMessage, new ParkDesignation("PARK3"));

             // Insert initial data into the database

            parkRepository.save(park1);
            parkRepository.save(park2);
            parkRepository.save(park3);
            // parkRepository.saveAll(Arrays.asList(park1, park2, park3));


            }

    }

    public ArrayList<ParkSpot> genParkSpots(){
        ArrayList<ParkSpot> parkSpots = new ArrayList<ParkSpot>();

        ParkSpotInternalId parkSpotId1 = ParkSpotInternalId.genNewId();
        VehicleCategory category1 = new VehicleCategory(VehicleCategoryEnum.Eletric);
        ParkSpot parkSpot1 = new ParkSpot(parkSpotId1,false, category1, 1);
        parkSpots.add(parkSpot1);

        ParkSpotInternalId parkSpotId2 = ParkSpotInternalId.genNewId();
        VehicleCategory category2 = new VehicleCategory(VehicleCategoryEnum.GPL);
        ParkSpot parkSpot2 = new ParkSpot(parkSpotId2,false, category2, 1);
        parkSpots.add(parkSpot2);

        ParkSpotInternalId parkSpotId3 = ParkSpotInternalId.genNewId();
        VehicleCategory category3 = new VehicleCategory(VehicleCategoryEnum.Gas);
        ParkSpot parkSpot3 = new ParkSpot(parkSpotId3,false, category3, 1);
        parkSpots.add(parkSpot3);

        ParkSpotInternalId parkSpotId4 = ParkSpotInternalId.genNewId();
        VehicleCategory category4 = new VehicleCategory(VehicleCategoryEnum.Eletric);
        ParkSpot parkSpot4 = new ParkSpot(parkSpotId4,false, category4, 1);
        parkSpots.add(parkSpot4);

        ParkSpotInternalId parkSpotId5 = ParkSpotInternalId.genNewId();
        VehicleCategory category5 = new VehicleCategory(VehicleCategoryEnum.GPL);
        ParkSpot parkSpot5 = new ParkSpot(parkSpotId5,false, category5, 1);
        parkSpots.add(parkSpot5);

        ParkSpotInternalId parkSpotId6 = ParkSpotInternalId.genNewId();
        VehicleCategory category6 = new VehicleCategory(VehicleCategoryEnum.Gas);
        ParkSpot parkSpot6 = new ParkSpot(parkSpotId6,false, category6, 1);
        parkSpots.add(parkSpot6);

        ParkSpotInternalId parkSpotId7 = ParkSpotInternalId.genNewId();
        VehicleCategory category7 = new VehicleCategory(VehicleCategoryEnum.Handicapped);
        ParkSpot parkSpot7 = new ParkSpot(parkSpotId7,false, category7, 1);
        parkSpots.add(parkSpot7);

        ParkSpotInternalId parkSpotId8 = ParkSpotInternalId.genNewId();
        VehicleCategory category8 = new VehicleCategory(VehicleCategoryEnum.Motorcycle);
        ParkSpot parkSpot8 = new ParkSpot(parkSpotId8,false, category8, 1);
        parkSpots.add(parkSpot8);

        return parkSpots;
    }
}
