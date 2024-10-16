package park20.Park_Microservice.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import park20.Park_Microservice.domain.Park.*;
import park20.Park_Microservice.dto.PriceTableDTO;
import park20.Park_Microservice.dto.RegisterPriceTableResponseDTO;
import park20.Park_Microservice.repository.ParkRepository;
import park20.Park_Microservice.repository.PriceTableRepository;
import park20.Park_Microservice.shared.Utils;

import java.time.LocalDate;
import java.util.List;


@Service
public class RegisterPriceTableService {

    protected RabbitTemplate rabbitTemplate;

    protected PriceTableRepository priceTableRepository;
    protected ParkRepository parkRepository;

    public RegisterPriceTableService(RabbitTemplate rabbitTemplate, PriceTableRepository priceTableRepository, ParkRepository parkRepository) {
        this.priceTableRepository = priceTableRepository;
        this.parkRepository = parkRepository;
        this.rabbitTemplate = rabbitTemplate;

    }

    public RegisterPriceTableResponseDTO register(PriceTableDTO priceTableDTO) throws Exception {

        List<PriceTablePeriod> priceTablePeriods = Utils.extractPriceTablePeriods(priceTableDTO.periods);
        PriceTableInternalId priceTableInternalId = PriceTableInternalId.genNewId();
        PriceTableEffectiveTime pt = new PriceTableEffectiveTime(LocalDate.parse(priceTableDTO.effectiveTime),false);

        PriceTable table = new PriceTable(priceTableInternalId,pt);

        for(PriceTablePeriod p : priceTablePeriods) {
            table.addPeriod(p);
        }
        Park p = this.parkRepository.getParkByDesignation_Designation(priceTableDTO.parkId);
        if(p == null){
            throw new IllegalArgumentException("Park with that parkId does not exist");
        }
        p.addPriceTable(table);
        Park savedCustomer = this.parkRepository.save(p);

        RegisterPriceTableResponseDTO responseDto = new RegisterPriceTableResponseDTO();
        responseDto.id = table.getId().toString();
        responseDto.parkId = p.getId().toString();

        return responseDto;
    }
}
