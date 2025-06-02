package datn.com.example.datn.sys.domain.Config;

import com.nimbusds.jwt.SignedJWT;
import datn.com.example.datn.sys.domain.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class CustomJwtDecoder implements JwtDecoder {
    @Autowired
    AuthenticationService authenticationService;

    public CustomJwtDecoder(AuthenticationService authenticationService ) {
        this.authenticationService = authenticationService;
    }
    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return new Jwt(token,
                    signedJWT.getJWTClaimsSet().getIssueTime().toInstant(),
                    signedJWT.getJWTClaimsSet().getExpirationTime().toInstant(),
                    signedJWT.getJWTClaimsSet().toJSONObject(),
                    signedJWT.getJWTClaimsSet().getClaims()
            );
        }
        catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
