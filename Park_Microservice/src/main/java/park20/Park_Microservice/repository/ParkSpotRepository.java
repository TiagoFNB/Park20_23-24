package park20.Park_Microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import park20.Park_Microservice.domain.Park.ParkSpot;

import java.util.List;


public interface ParkSpotRepository extends JpaRepository<ParkSpot, String> {

    @Query(value="SELECT * FROM park_spot ps INNER JOIN park_park_spots p ON p.park_spots_id = ps.id WHERE p.park_id = ?1 AND ps.is_ocupied = false AND ps.category = ?2 ORDER BY id ASC",nativeQuery = true)
    List<ParkSpot> findEmptyParkSlotForVehicleCategory(String parkId, String category);

    ParkSpot getParkSpotById_Id(String id);
}
