package park20.Customer_Microservice.service;

import org.springframework.stereotype.Service;
import park20.Customer_Microservice.domain.Stay.Stay;
import park20.Customer_Microservice.dto.StayDTO;
import park20.Customer_Microservice.repository.StayRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListStayService {

    StayRepository stayRepository;

    public ListStayService(StayRepository stayRepository) {
        this.stayRepository = stayRepository;
    }

    public List<StayDTO> get() throws Exception {
        List<StayDTO> result = new ArrayList<>();
        List<Stay> stays = this.stayRepository.findAll();

        for(Stay s : stays) {
            result.add(s.toDTO());
        }
        return result;
    }
}
