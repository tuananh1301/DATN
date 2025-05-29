package datn.com.example.datn.sys.domain.Service.ServiceImpl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import datn.com.example.datn.sys.domain.Dto.Authentication;
import datn.com.example.datn.sys.domain.Entity.NguoiDung;
import datn.com.example.datn.sys.domain.Repository.NguoiDungRepository;
import datn.com.example.datn.sys.domain.Service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
    NguoiDungRepository nguoiDungRepository;

    @NonFinal
    @Value("jwt.signerKey")
    String signerKey;

    @NonFinal
    @Value("jwt.valid-duration")
    long validDuration;

    @NonFinal
    @Value("jwt.refreshable-duration")
    long refreshDuration;

    @Override
    public String generateToken(NguoiDung nguoiDung) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(nguoiDung.getFullName())
                .issuer("lily-shop")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(validDuration, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("idNguoiDung", nguoiDung.getId())
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(signerKey.getBytes()));
            return jwsObject.serialize();
        }catch (JOSEException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generateRefreshToken(NguoiDung nguoiDung) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .expirationTime(new Date(Instant.now().plus(refreshDuration, ChronoUnit.SECONDS).toEpochMilli()))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(signerKey.getBytes()));
            return jwsObject.serialize();
        }catch (JOSEException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Authentication authenticate(NguoiDung nguoiDung) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        NguoiDung nguoiDung1 = nguoiDungRepository.findByUsername(nguoiDung.getFullName());
        boolean authenticated = passwordEncoder.matches(nguoiDung.getMatKhau(), nguoiDung1.getMatKhau());
        if (!authenticated) {
            throw new RuntimeException("invalid password");
        }
        var token = generateToken(nguoiDung);
        var refreshToken = generateRefreshToken(nguoiDung);
        return Authentication.builder().token(token).refreshToken(refreshToken).authenticated(true).build();
    }
}
