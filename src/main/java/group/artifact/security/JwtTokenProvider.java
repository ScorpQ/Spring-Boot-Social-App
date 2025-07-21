package group.artifact.security;

import java.net.Authenticator;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;



@Component
public class JwtTokenProvider {

    @Value("${questapp.app.secret}")
    private String APP_SECRET;
    
    @Value("${questapp.app.expires-in}")
    private Long EXPIRES_IN;

    public String generateJwtToken(Authentication auth) {
        JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal();
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
        return JWTVerifier.
    }

    public Long getUserIdFromJwt(String token) [
        Claims 
    ]
    
}
