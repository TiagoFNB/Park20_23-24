package park20.Park_Microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import park20.Park_Microservice.domain.Park.PriceTable;

import java.time.LocalDate;
import java.util.List;


public interface PriceTableRepository extends JpaRepository<PriceTable, String> {

    PriceTable getFirstByPark_Designation_DesignationAndEffectiveTime_EffectiveTimeIsLessThanEqualOrderByEffectiveTimeDesc(String designation, LocalDate effectiveTime);

}
