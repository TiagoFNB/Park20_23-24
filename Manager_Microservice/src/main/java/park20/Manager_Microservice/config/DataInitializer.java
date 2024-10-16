package park20.Manager_Microservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import park20.Manager_Microservice.domain.Manager.Manager;
import park20.Manager_Microservice.domain.Manager.ManagerInternalId;
import park20.Manager_Microservice.domain.Manager.ManagerPassword;
import park20.Manager_Microservice.domain.Manager.ManagerUsername;
import park20.Manager_Microservice.repository.ManagerRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ManagerRepository managerRepository;
    @Override
    public void run(String... args) throws Exception {
        if (managerRepository.count() == 0) {

            //Setup managers
            ManagerInternalId manId1 = ManagerInternalId.genNewId();
            ManagerUsername manUser1 = new ManagerUsername("tiago123");
            ManagerPassword manPass1 = new ManagerPassword("abc123");
            Manager man1 = new Manager(manId1, manPass1, manUser1);

            ManagerInternalId manId2 = ManagerInternalId.genNewId();
            ManagerUsername manUser2 = new ManagerUsername("vera123");
            ManagerPassword manPass2 = new ManagerPassword("abc123");
            Manager man2 = new Manager(manId2, manPass2, manUser2);

             // Insert initial data into the database
            managerRepository.save(man1);
            managerRepository.save(man2);
        }

    }
}
