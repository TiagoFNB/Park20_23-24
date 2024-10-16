package park20.Manager_Microservice.shared;

import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.reactive.function.client.WebClient;
import park20.Manager_Microservice.security.CustomUserDetails;

public class Utils {

    public static CustomUserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return userDetails;
        }

        return null;
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

    public static Object gate(WebClient webClientGate) {

        return webClientGate.post()
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
