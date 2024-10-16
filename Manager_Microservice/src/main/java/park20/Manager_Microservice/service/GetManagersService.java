package park20.Manager_Microservice.service;

import org.springframework.stereotype.Service;
import park20.Manager_Microservice.domain.Manager.Manager;
import park20.Manager_Microservice.domain.Manager.ManagerInternalId;
import park20.Manager_Microservice.dto.ManagerDTO;
import park20.Manager_Microservice.dto.ManagersDTO;
import park20.Manager_Microservice.repository.ManagerRepository;

import java.util.List;

@Service
public class GetManagersService {


    ManagerRepository managerRepository;

    public GetManagersService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public List<Manager> get() throws Exception {
        List<Manager> managers = this.managerRepository.findAll();
        return managers;
    }
}
