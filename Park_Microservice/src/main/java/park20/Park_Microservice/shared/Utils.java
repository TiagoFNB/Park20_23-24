package park20.Park_Microservice.shared;

import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import park20.Park_Microservice.domain.Park.PriceTableFraction;
import park20.Park_Microservice.domain.Park.PriceTablePeriod;
import park20.Park_Microservice.domain.Park.PriceTablePeriodInternalId;
import park20.Park_Microservice.dto.PriceTableFractionDTO;
import park20.Park_Microservice.dto.PriceTablePeriodDTO;
import park20.Park_Microservice.security.CustomUserDetails;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static CustomUserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return userDetails;
        }

        return null;
    }

    public static List<PriceTablePeriod> extractPriceTablePeriods(PriceTablePeriodDTO[] listDTO) throws Exception {
        if(listDTO.length == 0) {
            throw new Exception("PriceTableNeedsPeriods");
        }

        List<PriceTablePeriod> vehicles = new ArrayList<>();

        for(PriceTablePeriodDTO dto : listDTO) {
            PriceTablePeriodInternalId id = PriceTablePeriodInternalId.genNewId();
            LocalTime start = LocalTime.parse(dto.startTime);
            LocalTime end = LocalTime.parse(dto.endTime);
            ArrayList<PriceTableFraction> fractions = Utils.extractFractions(dto.fractions);

            PriceTablePeriod v = new  PriceTablePeriod(id,start,end,fractions);

            vehicles.add(v);
        }

        return vehicles;
    }

    public static ArrayList<PriceTableFraction> extractFractions(PriceTableFractionDTO[] listDTO) throws Exception {
        if(listDTO.length == 0) {
            throw new Exception("PeriodNeedsFractions");
        }
        ArrayList<PriceTableFraction> fractions = new ArrayList<>();

        for(PriceTableFractionDTO dto : listDTO) {
            PriceTableFraction fraction = new PriceTableFraction(dto.automobilePrice, dto.motorcyclePrice);
            fractions.add(fraction);
        }

        return fractions;
    }
    public static String HumanReadableConstraintViolation(DataIntegrityViolationException exception) {

        PSQLException rootCause = (PSQLException) exception.getRootCause();

        ServerErrorMessage err = rootCause.getServerErrorMessage();

        String table = err.getTable();
        String cause = err.getRoutine();

        table = Character.toUpperCase(table.charAt(0)) + table.substring(1);

        if(cause.contains("unique")) {
            cause = "Unique";
        }

        return table+cause+"Violation";
    }
}
