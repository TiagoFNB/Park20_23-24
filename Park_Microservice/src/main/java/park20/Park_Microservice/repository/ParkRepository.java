package park20.Park_Microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import park20.Park_Microservice.domain.Park.Park;
import park20.Park_Microservice.domain.Park.ParkSpot;

import java.util.List;


public interface ParkRepository extends JpaRepository<Park, String> {
    Park getParkById_Id(String id_id);
    Park getParkByDesignation_Designation(String designation);
    Park getParkByParkSpotsContains(ParkSpot ps);

    List<Park> findAll();


}
