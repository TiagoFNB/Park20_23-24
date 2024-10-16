package park20.Park_Microservice.domain.Park;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import park20.Park_Microservice.dto.Park2DTO;
import park20.Park_Microservice.dto.ParkWithSpotsAndParkyDTO;
import park20.Park_Microservice.shared.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "Park")
public class Park implements Entity<Park> {

    @EmbeddedId
    @JsonUnwrapped
    private ParkInternalId id;


    @Embedded
    @JsonUnwrapped
    private ParkAddress address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParkSpot> parkSpots;

    @JsonProperty
    @Column(nullable = false)
    private Integer spotsGas;

    @JsonProperty
    @Column(nullable = false)
    private Integer spotsGPL;

    @JsonProperty
    @Column(nullable = false)
    private Integer spotsElectric;

    @JsonProperty
    @Column(nullable = false)
    private Integer spotsHandicapped;

    @JsonProperty
    @Column(nullable = false)
    private Integer spotsMotorcycle;

    @JsonProperty
    @Column(nullable = false)
    private Integer parkyCoinGainHourly;

    @JsonProperty
    @Column(nullable = false)
    private Integer parkyCoinWorthInTime;

    @JsonProperty
    @Column(nullable = false)
    private Integer parkyCoinUsageMinimumTime;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PriceTable> priceTables;

    @Embedded
    @JsonUnwrapped
    private ParkWelcomeMessage welcomeMessage;

    @Embedded
    @JsonUnwrapped
    private ParkLeavingMessage leavingMessage;

    @Embedded
    @JsonUnwrapped
    private ParkDesignation designation;


    protected Park(){}

    public Park(ParkInternalId id, ParkAddress address, List<ParkSpot> parkSpots, Integer spotsGas, Integer spotsGPL, Integer spotsElectric, Integer spotsHandicapped, Integer spotsMotorcycle, Integer parkyCoinGainHourly, Integer parkyCoinWorthInTime, Integer parkyCoinUsageMinimumTime, ParkWelcomeMessage welcomeMessage, ParkLeavingMessage leavingMessage, ParkDesignation designation) {
        this.id = id;
        this.address = address;
        this.parkSpots = parkSpots;
        this.spotsGas = spotsGas;
        this.spotsGPL = spotsGPL;
        this.spotsElectric = spotsElectric;
        this.spotsHandicapped = spotsHandicapped;
        this.spotsMotorcycle = spotsMotorcycle;
        this.parkyCoinGainHourly = parkyCoinGainHourly;
        this.parkyCoinWorthInTime = parkyCoinWorthInTime;
        this.parkyCoinUsageMinimumTime = parkyCoinUsageMinimumTime;
        this.welcomeMessage = welcomeMessage;
        this.leavingMessage = leavingMessage;
        this.designation = designation;
        this.priceTables = new ArrayList<>();
    }

    /**
     * Adds price table to the list
     * @param pt
     */
    public void addPriceTable(PriceTable pt) {
        pt.setPark(this);
        this.priceTables.add(pt);
    }

    public List<PriceTable> getPriceTables() {
        return priceTables;
    }

    public ParkAddress getAddress() {
        return address;
    }

    public List<ParkSpot> getParkSpots() {
        return parkSpots;
    }

    public ParkWelcomeMessage getWelcomeMessage() {
        return welcomeMessage;
    }

    public ParkLeavingMessage getLeavingMessage() {
        return leavingMessage;
    }

    @Override
    public boolean sameIdentityAs(Park other) {
        return false;
    }

    public ParkInternalId getId() {
        return id;
    }

    public ParkDesignation getDesignation() {
        return designation;
    }


    public Integer getSpotsGas() {
        return spotsGas;
    }

    public void setSpotsGas(Integer spotsGas) {
        this.spotsGas = spotsGas;
    }

    public Integer getSpotsGPL() {
        return spotsGPL;
    }

    public void setSpotsGPL(Integer spotsGPL) {
        this.spotsGPL = spotsGPL;
    }

    public Integer getSpotsElectric() {
        return spotsElectric;
    }

    public void setSpotsElectric(Integer spotsElectric) {
        this.spotsElectric = spotsElectric;
    }

    public Integer getSpotsHandicapped() {
        return spotsHandicapped;
    }

    public void setSpotsHandicapped(Integer spotsHandicapped) {
        this.spotsHandicapped = spotsHandicapped;
    }

    public Integer getSpotsMotorcycle() {
        return spotsMotorcycle;
    }

    public void setSpotsMotorcycle(Integer spotsMotorcycle) {
        this.spotsMotorcycle = spotsMotorcycle;
    }

    public Integer getParkyCoinGainHourly() {
        return parkyCoinGainHourly;
    }

    public void setParkyCoinGainHourly(Integer parkyCoinGainHourly) {
        this.parkyCoinGainHourly = parkyCoinGainHourly;
    }

    public Integer getParkyCoinWorthInTime() {
        return parkyCoinWorthInTime;
    }

    public void setParkyCoinWorthInTime(Integer parkyCoinWorthInTime) {
        this.parkyCoinWorthInTime = parkyCoinWorthInTime;
    }

    public Integer getParkyCoinUsageMinimumTime() {
        return parkyCoinUsageMinimumTime;
    }

    public void setParkyCoinUsageMinimumTime(Integer parkyCoinUsageMinimumTime) {
        this.parkyCoinUsageMinimumTime = parkyCoinUsageMinimumTime;
    }

    public Park2DTO toDTO() {
        Park2DTO dto = new Park2DTO();
        dto.id = this.id.toString();
        return dto;
    }

    public ParkWithSpotsAndParkyDTO toDTOCoins() {
        ParkWithSpotsAndParkyDTO dto = new ParkWithSpotsAndParkyDTO();
        dto.parkId = this.id.toString();
        dto.parkyCoinGainHourly = this.parkyCoinGainHourly.toString();
        dto.parkyCoinUsageMinimumTime = this.parkyCoinUsageMinimumTime.toString();
        dto.parkyCoinWorthInTime = this.parkyCoinWorthInTime.toString();
        dto.spotsGas = this.spotsGas.toString();
        dto.spotsElectric=this.spotsElectric.toString();
        dto.spotsGPL=this.spotsGPL.toString();
        dto.spotsMotorcycle=this.spotsMotorcycle.toString();
        dto.spotsHandicapped=this.spotsHandicapped.toString();
        return dto;
    }
}
