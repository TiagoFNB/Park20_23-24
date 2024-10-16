package park20.Authentication_Microservice.shared;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTGenerator {

    private static final String SECRET_KEY = "ThisKeyIsASuperKeyBecauseItIsPrettyKeyAndIsAnAmusingKeyBecauseItKeysAllOverThePlace";
    private static final long EXPIRATION_TIME = 3600000; // 1 hour in milliseconds

    /**
     * Generates the token
     * @param username the username to be associated with the token
     * @param id the id associated with the token (can be either a customer or a manager id)
     * @param isCustomer yes if it's a customer, false if a manager
     * @return
     */
    public String generateToken(String username, String id, Boolean isCustomer) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("isCustomer", isCustomer);
        return doGenerateToken(claims, username);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME * 1000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public String getIdFromToken(String token) {
        return getAllClaimsFromToken(token).get("id", String.class);
    }

    public Boolean getIsCustomerFromToken(String token) {
        return getAllClaimsFromToken(token).get("isCustomer", Boolean.class);
    }


    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException e) {
            // Handle exceptions, log, or return false based on your use case
            return false;
        }
    }

}