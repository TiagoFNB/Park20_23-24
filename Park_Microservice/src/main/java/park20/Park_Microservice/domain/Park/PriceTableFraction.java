package park20.Park_Microservice.domain.Park;

import com.fasterxml.jackson.annotation.JsonProperty;
import park20.Park_Microservice.domain.Vehicle.VehicleCategoryEnum;
import park20.Park_Microservice.dto.PriceTableFractionDTO;
import park20.Park_Microservice.dto.PriceTablePeriodDTO;
import park20.Park_Microservice.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PriceTableFraction implements ValueObject<PriceTableFraction> {

    @JsonProperty
    @Column
    public Double automobilePrice;

    @JsonProperty
    @Column
    public Double motorcyclePrice;

    protected PriceTableFraction() {
    }

    public PriceTableFraction(Double automobilePrice, Double motorcyclePrice) {
        this.automobilePrice = automobilePrice;
        this.motorcyclePrice = motorcyclePrice;
    }

    public Double getAutomobilePrice() {
        return this.automobilePrice;
    }

    public Double getMotorcyclePrice() {
        return this.motorcyclePrice;
    }

    public Double getPrice(VehicleCategoryEnum category) {
        if(category == VehicleCategoryEnum.Motorcycle) {
            return this.motorcyclePrice;
        } else {
            return this.automobilePrice;
        }
    }

    public PriceTableFractionDTO toDto() {
        PriceTableFractionDTO dto = new PriceTableFractionDTO();
        dto.automobilePrice = automobilePrice;
        dto.motorcyclePrice = motorcyclePrice;

        return dto;
    }


    @Override
    public boolean sameValueAs(PriceTableFraction other) {
        return false;
    }
}
