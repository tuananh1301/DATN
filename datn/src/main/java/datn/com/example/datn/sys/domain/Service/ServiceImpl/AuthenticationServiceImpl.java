package datn.com.example.datn.sys.domain.Service.ServiceImpl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import datn.com.example.datn.exception.AppException;
import datn.com.example.datn.exception.ErrorCode;
import datn.com.example.datn.sys.domain.Dto.Request.*;
import datn.com.example.datn.sys.domain.Dto.Response.AuthenticationRes;
import datn.com.example.datn.sys.domain.Dto.Response.IntrospectRes;
import datn.com.example.datn.sys.domain.Entity.InvalidatedToken;
import datn.com.example.datn.sys.domain.Entity.NguoiDung;
import datn.com.example.datn.sys.domain.Entity.VaiTro;
import datn.com.example.datn.sys.domain.Repository.InvalidatedTokenRepository;
import datn.com.example.datn.sys.domain.Repository.NguoiDungRepository;
import datn.com.example.datn.sys.domain.Repository.httpclient.OutboundIdentityClient;
import datn.com.example.datn.sys.domain.Repository.httpclient.OutboundUserClient;
import datn.com.example.datn.sys.domain.Service.AuthenticationService;
import datn.com.example.datn.sys.domain.constant.PredefinedRole;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    NguoiDungRepository nguoiDungRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;
    OutboundUserClient outboundUserClient;
    OutboundIdentityClient outboundIdentityClient;
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;

    @NonFinal
    @Value("${outbound.identity.client-id}")
    protected String CLIENT_ID;

    @NonFinal
    @Value("${outbound.identity.client-secret}")
    protected String CLIENT_SECRET;

    @NonFinal
    @Value("${outbound.identity.redirect-uri}")
    protected String REDIRECT_URI;

    @NonFinal
    protected final String GRANT_TYPE = "authorization_code";

    @Override
    public String generateToken(NguoiDung nguoiDung) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(nguoiDung.getTenDangNhap())
                .issuer("lily-shop")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("idNguoiDung", nguoiDung.getId())
                .claim("scope", buildScope(nguoiDung))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        }catch (JOSEException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generateRefreshToken(NguoiDung nguoiDung) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .expirationTime(new Date(Instant.now()
                        .plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS)
                        .toEpochMilli()))
                .issueTime(new Date())
                .jwtID(UUID.randomUUID().toString())
                .subject(nguoiDung.getTenDangNhap())
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        }catch (JOSEException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public AuthenticationRes authenticate(AuthenticationReq req) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var nguoiDung1 = nguoiDungRepository.findByTenDangNhap(req.getTenDangNhap()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));
        boolean authenticated = passwordEncoder.matches(req.getMatKhau(), nguoiDung1.getMatKhau());
        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED_ERROR);
        }
        var token = generateToken(nguoiDung1);
        var refreshToken = generateRefreshToken(nguoiDung1);
        return AuthenticationRes.builder().token(token).refreshToken(refreshToken).authenticated(true).build();
    }

    @Override
    public SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = (isRefresh) ? new Date(signedJWT
                .getJWTClaimsSet()
                .getIssueTime()
                .toInstant()
                .plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS)
                .toEpochMilli()) : signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);
        if(!(verified && expiryTime.after(new Date()))) {
            throw new AppException(ErrorCode.UNAUTHENTICATED_ERROR);
        }
        return signedJWT;
    }

    @Override
    public String buildScope(NguoiDung nguoiDung) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(nguoiDung.getVaiTro()))
            nguoiDung.getVaiTro().forEach(vaitro -> {
                stringJoiner.add("ROLE_" + vaitro.getName());
            });
        return stringJoiner.toString();

    }

    @Override
    public AuthenticationRes refreshToken(RefreshReq request) throws ParseException, JOSEException {
        var signedJWT = verifyToken(request.getToken(), true);
        var jit = signedJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        InvalidatedToken invalidatedToken = InvalidatedToken.builder().id(jit).expiryTime(expiryTime).build();
        invalidatedTokenRepository.save(invalidatedToken);
        var username = signedJWT.getJWTClaimsSet().getSubject();
        var nguoiDung = nguoiDungRepository.findByTenDangNhap(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));
        var token = generateToken(nguoiDung);
        var refreshToken = generateRefreshToken(nguoiDung);

        return AuthenticationRes.builder().token(token).refreshToken(refreshToken).authenticated(true).build();
    }

    @Override
    public void logout(LogoutReq request) throws ParseException, JOSEException{
        try {
            var signToken = verifyToken(request.getToken(), true);

            String jit = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken =
                    InvalidatedToken.builder().id(jit).expiryTime(expiryTime).build();

            invalidatedTokenRepository.save(invalidatedToken);
        } catch (AppException e) {
            log.info("Token already expired");
        }
    }

    @Override
    public IntrospectRes introspect(IntrospectReq request) throws ParseException, JOSEException {
        var token = request.getToken();
        boolean isValid = true;

        try {
            verifyToken(token, false);
        } catch (AppException e) {
            isValid = false;
        }

        return IntrospectRes.builder().valid(isValid).build();
    }

    @Override
    public AuthenticationRes outboundAuthentication(String code) {
        var response = outboundIdentityClient.exchangeToken(ExchangeTokenRequest.builder()
                .code(code)
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .redirectUri(REDIRECT_URI)
                .grantType(GRANT_TYPE)
                .build());

        log.info("TOKEN RESPONSE {}", response);

        var userInfo = outboundUserClient.getUserInfo("json", response.getAccessToken());

        log.info("User Info {}", userInfo);
        Set<VaiTro> vaiTro = new HashSet<>();
        vaiTro.add(VaiTro.builder().name(PredefinedRole.CUSTOMER_ROLE).build());
        var user = nguoiDungRepository.findByTenDangNhap(userInfo.getEmail()).orElseGet(
                () -> nguoiDungRepository.save(NguoiDung.builder()
                        .tenDangNhap(userInfo.getEmail())
                        .vaiTro(vaiTro)
                        .fullName(userInfo.getName())
                        .build()));

        var token = generateToken(user);
        var refreshToken = generateRefreshToken(user);

        return AuthenticationRes.builder()
                .token(token)
                .refreshToken(refreshToken)
                .build();
    }
}
