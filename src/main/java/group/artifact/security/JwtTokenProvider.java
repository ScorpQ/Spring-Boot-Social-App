package group.artifact.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    @Value("${questapp.app.secret}")
    private String APP_SECRET;
    
    @Value("${questapp.app.expires-in}")
    private Long EXPIRES_IN;
    
}
