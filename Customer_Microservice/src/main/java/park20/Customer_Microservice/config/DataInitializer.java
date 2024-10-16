package park20.Customer_Microservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import park20.Customer_Microservice.domain.Customer.*;
import park20.Customer_Microservice.domain.Stay.*;
import park20.Customer_Microservice.domain.Vehicle.*;
import park20.Customer_Microservice.repository.CustomerRepository;
import park20.Customer_Microservice.repository.StayRepository;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private StayRepository stayRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public void run(String... args) throws Exception {
        if (customerRepository.count() == 0 || stayRepository.count() == 0) {
            //CUSTOMERS

            //Customer1
            CustomerInternalId id1 = new CustomerInternalId("idCustomer1");
            CustomerEmail email1 = new CustomerEmail("email1@email.com");
            CustomerPassword pass1 = new CustomerPassword("abc123");
            CustomerUsername username1 = new CustomerUsername("user1cc");
            CustomerName name1 = new CustomerName("Alfredo");
            CustomerNif nif1 = new CustomerNif("123456789");
            Customer customer1 = new Customer(id1,email1,pass1,username1,name1,nif1, false);

            VehicleInternalId vehicleId1 = new VehicleInternalId("idVehicle1");
            VehicleLicensePlate plate1 = new VehicleLicensePlate("AA-00-AA");
            VehicleBrand brand1 = new VehicleBrand("Toyota");
            VehicleModel model1 = new VehicleModel("Yaris");
            VehicleCategory category1 = new VehicleCategory(VehicleCategoryEnum.Gas);
            Vehicle vehicle1 = new Vehicle(vehicleId1,plate1,brand1,model1,category1);

            customer1.addVehicle(vehicle1);
            customer1.getWallet().addCoins(10);

            customerRepository.save(customer1);

            // Customer 2
            CustomerInternalId id2 = new CustomerInternalId("idCustomer2");
            CustomerEmail email2 = new CustomerEmail("email2@email.com");
            CustomerPassword pass2 = new CustomerPassword("abc123");
            CustomerUsername username2 = new CustomerUsername("user2cc");
            CustomerName name2 = new CustomerName("Bianca");
            CustomerNif nif2 = new CustomerNif("987654321");
            Customer customer2 = new Customer(id2, email2, pass2, username2, name2, nif2, false);

            VehicleInternalId vehicleId2 = new VehicleInternalId("idVehicle2");
            VehicleLicensePlate plate2 = new VehicleLicensePlate("BB-11-BB");
            VehicleBrand brand2 = new VehicleBrand("Tesla");
            VehicleModel model2 = new VehicleModel("Model S");
            VehicleCategory category2 = new VehicleCategory(VehicleCategoryEnum.Eletric);
            Vehicle vehicle2 = new Vehicle(vehicleId2, plate2, brand2, model2, category2);

            VehicleInternalId vehicleId5 = new VehicleInternalId("idVehicle5");
            VehicleLicensePlate plate5 = new VehicleLicensePlate("ZS-11-ZS");
            VehicleBrand brand5 = new VehicleBrand("Yamaha");
            VehicleModel model5 = new VehicleModel("YZ85");
            VehicleCategory category5 = new VehicleCategory(VehicleCategoryEnum.Motorcycle);
            Vehicle vehicle5 = new Vehicle(vehicleId5, plate5, brand5, model5, category5);

            customer2.addVehicle(vehicle2);
            customer2.addVehicle(vehicle5);
            customer2.getWallet().addCoins(10);
            customerRepository.save(customer2);

            // Customer 3
            CustomerInternalId id3 = new CustomerInternalId("idCustomer3");
            CustomerEmail email3 = new CustomerEmail("email3@email.com");
            CustomerPassword pass3 = new CustomerPassword("abc123");
            CustomerUsername username3 = new CustomerUsername("user3cc");
            CustomerName name3 = new CustomerName("Carlos");
            CustomerNif nif3 = new CustomerNif("987123456");
            Customer customer3 = new Customer(id3, email3, pass3, username3, name3, nif3, false);

            VehicleInternalId vehicleId3 = new VehicleInternalId("idVehicle3");
            VehicleLicensePlate plate3 = new VehicleLicensePlate("CC-22-CC");
            VehicleBrand brand3 = new VehicleBrand("Ford");
            VehicleModel model3 = new VehicleModel("Focus");
            VehicleCategory category3 = new VehicleCategory(VehicleCategoryEnum.Gas);
            Vehicle vehicle3 = new Vehicle(vehicleId3, plate3, brand3, model3, category3);

            VehicleInternalId vehicleId4 = new VehicleInternalId("idVehicle4");
            VehicleLicensePlate plate4 = new VehicleLicensePlate("CD-22-CG");
            VehicleBrand brand4 = new VehicleBrand("Fiat");
            VehicleModel model4 = new VehicleModel("Punto");
            VehicleCategory category4 = new VehicleCategory(VehicleCategoryEnum.GPL);
            Vehicle vehicle4 = new Vehicle(vehicleId4, plate4, brand4, model4, category4);

            customer3.addVehicle(vehicle3);
            customer3.addVehicle(vehicle4);
            customer3.getWallet().addCoins(10);
            customerRepository.save(customer3);



            //STAYS

            // 2023/12
            StayInternalId idStay1= StayInternalId.genNewId();
            StayParkId idPark1 = new StayParkId("idPark1");
            StaySlotId slotId1 = new StaySlotId("slotId1");
            StayStartTime start1 = new StayStartTime(LocalDateTime.of(2023, 12, 10, 12, 30, 0));
            StayEndTime end1 = new StayEndTime(LocalDateTime.of(2023, 12, 10, 13, 30, 0));
            Stay stayDec1 = new Stay(idStay1,start1,idPark1,vehicle5,slotId1);
            stayDec1.addStayEndTime(end1);

            StayInternalId idStayDec2 = StayInternalId.genNewId();
            StayParkId idParkDec2 = new StayParkId("idPark2");
            StaySlotId slotIdDec2 = new StaySlotId("slotIdDec2");
            StayStartTime startDec2 = new StayStartTime(LocalDateTime.of(2023, 12, 12, 14, 0, 0));
            StayEndTime endDec2 = new StayEndTime(LocalDateTime.of(2023, 12, 12, 16, 0, 0));
            Stay stayDec2 = new Stay(idStayDec2, startDec2, idParkDec2, vehicle1, slotIdDec2);
            stayDec2.addStayEndTime(endDec2);

            StayInternalId idStayDec3 = StayInternalId.genNewId();
            StayParkId idParkDec3 = new StayParkId("idPark3");
            StaySlotId slotIdDec3 = new StaySlotId("slotIdDec3");
            StayStartTime startDec3 = new StayStartTime(LocalDateTime.of(2023, 12, 15, 10, 0, 0));
            StayEndTime endDec3 = new StayEndTime(LocalDateTime.of(2023, 12, 15, 12, 0, 0));
            Stay stayDec3 = new Stay(idStayDec3, startDec3, idParkDec3, vehicle3, slotIdDec3);
            stayDec3.addStayEndTime(endDec3);

            StayInternalId idStay4= StayInternalId.genNewId();
            StayParkId idPark4 = new StayParkId("idPark1");
            StaySlotId slotId4 = new StaySlotId("slotId1");
            StayStartTime start4 = new StayStartTime(LocalDateTime.of(2023, 12, 10, 13, 30, 0));
            StayEndTime end4 = new StayEndTime(LocalDateTime.of(2023, 12, 10, 14, 30, 0));
            Stay stayDec4 = new Stay(idStay4,start4,idPark4,vehicle5,slotId4);
            stayDec4.addStayEndTime(end4);

            StayInternalId idStayDec5 = StayInternalId.genNewId();
            StayParkId idParkDec5 = new StayParkId("idPark1");
            StaySlotId slotIdDec5 = new StaySlotId("slotIdDec1");
            StayStartTime startDec5 = new StayStartTime(LocalDateTime.of(2023, 12, 10, 12, 30, 0));
            StayEndTime endDec5 = new StayEndTime(LocalDateTime.of(2023, 12, 10, 13, 30, 0));
            Stay stayDec5 = new Stay(idStayDec5, startDec5, idParkDec5, vehicle3, slotIdDec5);
            stayDec5.addStayEndTime(endDec5);

            StayInternalId idStayDec6 = StayInternalId.genNewId();
            StayParkId idParkDec6 = new StayParkId("idPark1");
            StaySlotId slotIdDec6 = new StaySlotId("slotIdDec1");
            StayStartTime startDec6 = new StayStartTime(LocalDateTime.of(2023, 12, 16, 16, 30, 0));
            StayEndTime endDec6 = new StayEndTime(LocalDateTime.of(2023, 12, 16, 17, 30, 0));
            Stay stayDec6 = new Stay(idStayDec6, startDec6, idParkDec6, vehicle2, slotIdDec6);
            stayDec6.addStayEndTime(endDec6);

            StayInternalId idStayDec7 = StayInternalId.genNewId();
            StayParkId idParkDec7 = new StayParkId("idPark1");
            StaySlotId slotIdDec7 = new StaySlotId("slotIdDec1");
            StayStartTime startDec7 = new StayStartTime(LocalDateTime.of(2023, 12, 16, 16, 30, 0));
            StayEndTime endDec7 = new StayEndTime(LocalDateTime.of(2023, 12, 16, 17, 30, 0));
            Stay stayDec7 = new Stay(idStayDec7, startDec7, idParkDec7, vehicle2, slotIdDec7);
            stayDec7.addStayEndTime(endDec7);

            StayInternalId idStayDec8 = StayInternalId.genNewId();
            StayParkId idParkDec8 = new StayParkId("idPark1");
            StaySlotId slotIdDec8 = new StaySlotId("slotIdDec1");
            StayStartTime startDec8 = new StayStartTime(LocalDateTime.of(2023, 12, 16, 16, 30, 0));
            StayEndTime endDec8 = new StayEndTime(LocalDateTime.of(2023, 12, 16, 17, 30, 0));
            Stay stayDec8 = new Stay(idStayDec8, startDec8, idParkDec8, vehicle4, slotIdDec8);
            stayDec8.addStayEndTime(endDec8);

            stayRepository.save(stayDec1);
            stayRepository.save(stayDec2);
            stayRepository.save(stayDec3);
            stayRepository.save(stayDec4);
            stayRepository.save(stayDec5);
            stayRepository.save(stayDec6);
            stayRepository.save(stayDec7);
            stayRepository.save(stayDec8);

            // 2024/01
            StayInternalId idStay1b= StayInternalId.genNewId();
            StayParkId idPark1b = new StayParkId("idPark1");
            StaySlotId slotId1b = new StaySlotId("slotId1");
            StayStartTime start1b = new StayStartTime(LocalDateTime.of(2024, 1, 10, 12, 30, 0));
            StayEndTime end1b = new StayEndTime(LocalDateTime.of(2024, 1, 10, 13, 30, 0));
            Stay stayDec1b = new Stay(idStay1b,start1b,idPark1b,vehicle1,slotId1b);
            stayDec1b.addStayEndTime(end1b);

            StayInternalId idStayDec2b = StayInternalId.genNewId();
            StayParkId idParkDec2b = new StayParkId("idPark2");
            StaySlotId slotIdDec2b = new StaySlotId("slotIdDec2");
            StayStartTime startDec2b = new StayStartTime(LocalDateTime.of(2024, 1, 12, 14, 0, 0));
            StayEndTime endDec2b = new StayEndTime(LocalDateTime.of(2024, 1, 12, 16, 0, 0));
            Stay stayDec2b = new Stay(idStayDec2b, startDec2b, idParkDec2b, vehicle1, slotIdDec2b);
            stayDec2b.addStayEndTime(endDec2b);

            StayInternalId idStayDec3b = StayInternalId.genNewId();
            StayParkId idParkDec3b = new StayParkId("idPark3");
            StaySlotId slotIdDec3b = new StaySlotId("slotIdDec3");
            StayStartTime startDec3b = new StayStartTime(LocalDateTime.of(2024, 1, 15, 10, 0, 0));
            StayEndTime endDec3b = new StayEndTime(LocalDateTime.of(2024, 1, 15, 12, 0, 0));
            Stay stayDec3b = new Stay(idStayDec3b, startDec3b, idParkDec3b, vehicle3, slotIdDec3b);
            stayDec3b.addStayEndTime(endDec3b);

            StayInternalId idStay4b= StayInternalId.genNewId();
            StayParkId idPark4b = new StayParkId("idPark1");
            StaySlotId slotId4b = new StaySlotId("slotId1");
            StayStartTime start4b = new StayStartTime(LocalDateTime.of(2024, 1, 10, 13, 30, 0));
            StayEndTime end4b = new StayEndTime(LocalDateTime.of(2024, 1, 10, 14, 30, 0));
            Stay stayDec4b = new Stay(idStay4b,start4b,idPark4b,vehicle4,slotId4b);
            stayDec4b.addStayEndTime(end4b);

            stayRepository.save(stayDec1b);
            stayRepository.save(stayDec2b);
            stayRepository.save(stayDec3b);
            stayRepository.save(stayDec4b);
        }





    }


}
