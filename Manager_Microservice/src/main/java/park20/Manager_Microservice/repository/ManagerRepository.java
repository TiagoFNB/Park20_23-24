package park20.Manager_Microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import park20.Manager_Microservice.domain.Manager.Manager;
import park20.Manager_Microservice.domain.Manager.ManagerInternalId;
import park20.Manager_Microservice.domain.Manager.ManagerUsername;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, String> {


    Manager getManagerById(ManagerInternalId id);

    @Query("SELECT e FROM Manager e JOIN FETCH e.parks WHERE e.id.id = :id_id")
    Manager getManagerById_Id(String id_id);

    Manager getManagerByUsername(ManagerUsername username);
}
