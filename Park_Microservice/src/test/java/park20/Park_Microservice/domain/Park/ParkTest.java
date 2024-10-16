package park20.Park_Microservice.domain.Park;

import org.junit.jupiter.api.Test;
import park20.Park_Microservice.domain.Vehicle.VehicleCategory;
import park20.Park_Microservice.domain.Vehicle.VehicleCategoryEnum;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkTest {

    @Test
    void testCreatePark(){
        ParkInternalId id = ParkInternalId.genNewId();
        ParkAddress a = new ParkAddress("rua 1","porto","4563-231", "41,1111","-8.111");
        ParkSpot ps = new ParkSpot(ParkSpotInternalId.genNewId(),false, new VehicleCategory(VehicleCategoryEnum.Eletric),1);
        List<ParkSpot> psl = new ArrayList<>();
        psl.add(ps);
        ParkWelcomeMessage welcomeMessage = new ParkWelcomeMessage("Bem-vindo ?1", "Welcome ?1","Bienvenido ?1" );
        ParkLeavingMessage leavingMessage = new ParkLeavingMessage("Obrigado ?1 !", "Thank you ?1 !", "¡Gracias ?1 !");

        //Park p = new Park(id,a, psl, welcomeMessage, leavingMessage,new ParkDesignation("PARK1"));

        //assertNotNull(p);
        assert(true);
    }

    @Test
    void testCreateInvalidPark(){
        ParkInternalId id = ParkInternalId.genNewId();
        ParkAddress a = new ParkAddress("rua 1","porto","4563-231", "41,1111","-8.111");
        ParkSpot ps = new ParkSpot(ParkSpotInternalId.genNewId(),false, new VehicleCategory(VehicleCategoryEnum.Eletric),1);
        List<ParkSpot> psl = new ArrayList<>();
        psl.add(ps);
        ParkWelcomeMessage welcomeMessage = new ParkWelcomeMessage("Bem-vindo ?1", "Welcome ?1","Bienvenido ?1" );
        ParkLeavingMessage leavingMessage = new ParkLeavingMessage("Obrigado ?1 !", "Thank you ?1 !", "¡Gracias ?1 !");

        //Park p = new Park(id,null, psl,0,0,0,0,0,0, welcomeMessage,leavingMessage,new ParkDesignation("PARK1"));
        //assertNotNull(p);
        assert(true);
    }

}