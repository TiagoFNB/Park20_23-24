package park20.Park_Microservice.domain.Park;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.apache.commons.lang3.Validate;
import park20.Park_Microservice.domain.Vehicle.VehicleCategoryEnum;
import park20.Park_Microservice.dto.PriceTableDTO;
import park20.Park_Microservice.dto.PriceTableFractionDTO;
import park20.Park_Microservice.dto.PriceTablePeriodDTO;
import park20.Park_Microservice.dto.RemainingDTO;
import park20.Park_Microservice.shared.Entity;
import park20.Park_Microservice.shared.ValueObject;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "PriceTable")
public class PriceTable implements Entity<PriceTable> {

    @EmbeddedId
    @JsonUnwrapped
    private PriceTableInternalId id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PriceTablePeriod> periods;

    @Embedded
    @JsonUnwrapped
    private PriceTableEffectiveTime effectiveTime;

    @ManyToOne
    @JoinColumn(name = "park_id", referencedColumnName="id", nullable = false)
    private Park park;

    protected PriceTable() {
    }

    public PriceTable(PriceTableInternalId id, PriceTableEffectiveTime effectiveTime) {
        Validate.notNull(effectiveTime);
        this.id = id;
        this.effectiveTime = effectiveTime;
        this.periods = new ArrayList<>();
    }

    /**
     * Adds period to the list (only used for a new table)
     * @param p
     */
    public void addPeriod(PriceTablePeriod p) {
        p.setPriceTable(this);
        this.periods.add(p);
    }

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    public PriceTableInternalId getId(){
        return id;
    }

    public Double calculatePrice(LocalDateTime entryDate, LocalDateTime exitDate, VehicleCategoryEnum vehicleType) {
        Double totalPrice = 0.0;
        LocalDateTime currentTime = entryDate;
        Duration totalDuration = Duration.between(entryDate, exitDate);

        PriceTablePeriod previousPeriod = getCorrespondingPeriod(entryDate);

        RemainingDTO dto = previousPeriod.getPriceFromPeriod(currentTime.toLocalTime(),totalDuration,vehicleType);
        totalPrice = totalPrice + dto.total;
        totalDuration = dto.remainingDuration;


        //Get the period index
        int i = periods.indexOf(previousPeriod);

        while(totalDuration.toMinutes() > 0) {
            for(;i<periods.size();i++) {
                if(totalDuration.isNegative()) {
                    return totalPrice;
                }

                previousPeriod = periods.get(i);

                RemainingDTO dto2 = previousPeriod.getPriceFromPeriod(currentTime.toLocalTime(),totalDuration,vehicleType);
                totalPrice = totalPrice + dto2.total;
                totalDuration = dto2.remainingDuration;
            }
            i=0;
        }

        return totalPrice;
    }

    public PriceTablePeriod getCorrespondingPeriod(LocalDateTime entry) {
        LocalTime target = entry.toLocalTime();

        for(PriceTablePeriod period : this.periods) {
            if(period.isBetween(target)) {
                return period;
            }
        }

        return null;
    }

    public PriceTableDTO toDto() {
        PriceTableDTO dto = new PriceTableDTO();
        dto.id = id.toString();
        dto.effectiveTime = effectiveTime.toString();
        dto.parkId = park.getDesignation().toString();

        ArrayList<PriceTablePeriodDTO> ar = new ArrayList<>();
        for(PriceTablePeriod p: periods){
            PriceTablePeriodDTO priceTablePeriodDTO = p.toDto();
            ar.add(priceTablePeriodDTO);
        }
        dto.periods = ar.toArray(new PriceTablePeriodDTO[ar.size()]);

        return dto;
    }


    @Override
    public boolean sameIdentityAs(PriceTable other) {
        return this.id.sameValueAs(other.id);
    }
}
