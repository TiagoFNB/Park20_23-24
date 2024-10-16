package park20.Manager_Microservice.service;

import org.springframework.stereotype.Service;
import park20.Manager_Microservice.domain.Manager.CustomerLanguagesEnum;
import park20.Manager_Microservice.domain.Manager.Manager;
import park20.Manager_Microservice.domain.Manager.ManagerInternalId;
import park20.Manager_Microservice.dto.ChangeLanguageDTO;
import park20.Manager_Microservice.repository.ManagerRepository;
import park20.Manager_Microservice.shared.Utils;

@Service
public class ChangeManagerLanguageService {

    ManagerRepository managerRepository;

    public ChangeManagerLanguageService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public void change(ChangeLanguageDTO changeLanguageDTO) throws Exception {
        Manager c = this.managerRepository.getManagerById(new ManagerInternalId(Utils.getUserDetails().getId()));
        if(c == null) {
            throw new Exception("CustomerNotExist");
        }

        c.changeLanguage(CustomerLanguagesEnum.valueOf(changeLanguageDTO.language));
        this.managerRepository.save(c);
    }
}
