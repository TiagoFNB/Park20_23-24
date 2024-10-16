package park20.Park_Microservice.service;

import org.springframework.stereotype.Service;
import park20.Park_Microservice.domain.Park.PriceTable;
import park20.Park_Microservice.domain.Vehicle.VehicleCategoryEnum;
import park20.Park_Microservice.repository.ParkRepository;
import park20.Park_Microservice.repository.PriceTableRepository;

import java.time.LocalDateTime;

@Service
public class EmitPaymentService {

    PriceTableRepository priceTableRepository;

    public EmitPaymentService(PriceTableRepository priceTableRepository) {
        this.priceTableRepository = priceTableRepository;
    }

    public Double emit(String stayStartTime,String stayEndTime, String cat, String parkDesignation) {

        /**
        PriceTableFraction fraction1 = new PriceTableFraction(0.35,.015);
        PriceTableFraction fraction2 = new PriceTableFraction(0.15,.005);
        PriceTableFraction fraction3 = new PriceTableFraction(0.25,.010);
        PriceTableFraction fraction4 = new PriceTableFraction(0.25,.010);
        PriceTableFraction fraction5 = new PriceTableFraction(0.30,.015);

        ArrayList<PriceTableFraction> fractions1 = new ArrayList<>();
        fractions1.add(fraction1);
        fractions1.add(fraction2);
        fractions1.add(fraction3);
        fractions1.add(fraction4);
        fractions1.add(fraction5);


        PriceTableFraction fraction6 = new PriceTableFraction(0.30,.015);
        ArrayList<PriceTableFraction> fractions2 = new ArrayList<>();
        fractions2.add(fraction6);


        PriceTablePeriod period1 = new PriceTablePeriod(LocalTime.of(8,0), LocalTime.of(22,0),fractions1);
        PriceTablePeriod period2 = new PriceTablePeriod(LocalTime.of(22,0), LocalTime.of(8,0),fractions2);
        ArrayList<PriceTablePeriod> periods = new ArrayList<>();
        periods.add(period1);
        periods.add(period2);

        PriceTableEffectiveTime pt = new PriceTableEffectiveTime(LocalDate.now(),true);

        PriceTable table = new PriceTable(periods,pt);
        */

        LocalDateTime start = LocalDateTime.parse(stayStartTime);
        LocalDateTime end = LocalDateTime.parse(stayEndTime);
        //Fetch latest active price tables for current prices
        PriceTable table = this.priceTableRepository.getFirstByPark_Designation_DesignationAndEffectiveTime_EffectiveTimeIsLessThanEqualOrderByEffectiveTimeDesc(parkDesignation,start.toLocalDate());
        Double total = 0.0;
        if(table != null){
           total = table.calculatePrice(start,end, VehicleCategoryEnum.valueOf(cat));
        }


        return total;
    }
}
