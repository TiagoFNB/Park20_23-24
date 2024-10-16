package park20.Manager_Microservice.service;

import org.springframework.stereotype.Service;
import park20.Manager_Microservice.repository.ManagerRepository;
import park20.Manager_Microservice.domain.Manager.Manager;
import park20.Manager_Microservice.domain.Manager.ManagerInternalId;
import park20.Manager_Microservice.dto.ManagerDTO;

@Service
public class GetManagerService {

    ManagerRepository managerRepository;

    public GetManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public ManagerDTO get(ManagerInternalId id) throws Exception {
        Manager c = this.managerRepository.getManagerById(id);
        if(c == null) {
            throw new Exception("ManagerNotExist");
        }

        return c.toDto();
    }
}
