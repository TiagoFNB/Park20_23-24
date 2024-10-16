package park20.Park_Microservice.domain.Park;

import org.junit.jupiter.api.Test;
import park20.Park_Microservice.domain.Vehicle.VehicleCategory;
import park20.Park_Microservice.domain.Vehicle.VehicleCategoryEnum;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PriceTableTest {

    @Test
    void testCreatePriceTable(){
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

        PriceTablePeriodInternalId idp1 = PriceTablePeriodInternalId.genNewId();
        PriceTablePeriodInternalId idp2 = PriceTablePeriodInternalId.genNewId();
        PriceTablePeriod period1 = new PriceTablePeriod(idp1,LocalTime.of(8,0), LocalTime.of(22,0),fractions1);
        PriceTablePeriod period2 = new PriceTablePeriod(idp2,LocalTime.of(22,0), LocalTime.of(8,0),fractions2);


        PriceTableEffectiveTime pt = new PriceTableEffectiveTime(LocalDate.now(),true);

        PriceTableInternalId id = PriceTableInternalId.genNewId();

        PriceTable table = new PriceTable(id,pt);
        table.addPeriod(period1);
        table.addPeriod(period2);

        assertNotNull(table);
    }

    @Test
    void testCreateInvalidPark(){
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

        PriceTablePeriodInternalId idp1 = PriceTablePeriodInternalId.genNewId();
        PriceTablePeriodInternalId idp2 = PriceTablePeriodInternalId.genNewId();
        PriceTablePeriod period1 = new PriceTablePeriod(idp1,LocalTime.of(8,0), LocalTime.of(22,0),fractions1);
        PriceTablePeriod period2 = new PriceTablePeriod(idp2,LocalTime.of(22,0), LocalTime.of(8,0),fractions2);


        PriceTableEffectiveTime pt = null;

        PriceTableInternalId id = PriceTableInternalId.genNewId();

        assertThrows(Exception.class, () -> {
            PriceTable table = new PriceTable(id,pt);
        });
    }

}