package park20.Park_Microservice.service;

import org.springframework.stereotype.Service;
import park20.Park_Microservice.domain.Park.Park;
import park20.Park_Microservice.dto.ParkWithSpotsAndParkyDTO;
import park20.Park_Microservice.repository.ParkRepository;

@Service
public class ParkConfigService {

    protected ParkRepository parkRepository;

    public ParkConfigService(ParkRepository parkRepository) {
        this.parkRepository = parkRepository;
    }

    public void save(ParkWithSpotsAndParkyDTO dto) throws Exception {
        Park park = this.parkRepository.getParkById_Id(dto.parkId);

        park.setParkyCoinGainHourly(Integer.parseInt(dto.parkyCoinGainHourly));
        park.setParkyCoinWorthInTime(Integer.parseInt(dto.parkyCoinWorthInTime));
        park.setParkyCoinUsageMinimumTime(Integer.parseInt(dto.parkyCoinUsageMinimumTime));
        park.setSpotsGas(Integer.parseInt(dto.spotsGas));
        park.setSpotsElectric(Integer.parseInt(dto.spotsElectric));
        park.setSpotsGPL(Integer.parseInt(dto.spotsGPL));
        park.setSpotsHandicapped(Integer.parseInt(dto.spotsHandicapped));
        park.setSpotsMotorcycle(Integer.parseInt(dto.spotsMotorcycle));

        this.parkRepository.save(park);
    }
}
