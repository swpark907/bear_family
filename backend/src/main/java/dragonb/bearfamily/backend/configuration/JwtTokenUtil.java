package dragonb.bearfamily.backend.configuration;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import dragonb.bearfamily.backend.model.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

// JWT 토큰 관련 설정 담당하는 클래스
@Component
@PropertySource("classpath:jwt.properties")
public class JwtTokenUtil implements Serializable{
    private static final long serialVersionUID = -8522131160021027358L;
    // public static final long JWT_ACCESS_TOKEN_VALIDITY_TIME = 30 * 60 * 1000; // 30minute
    public static final long JWT_REFRESH_TOKEN_VALIDITY_TIME = 15 * 24 * 60 * 60 * 1000; // 15day
    public static final long JWT_ACCESS_TOKEN_VALIDITY_TIME = 15 * 60 * 1000; // 10 second

    // @Value("${jwt.secret}")
    // private String secret;

    @Value("${jwt.access.secret}")
    private String accessSecret;

    @Value("${jwt.refresh.secret}")
    private String refreshSecret;

    //retrieve username from jwt token
    // 토큰으로부터 username 획득
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    // 토큰으로부터 만료일자 획득
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // 토큰으로부터 정보 반환
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        //System.out.println(claims.toString());
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    // 시크릿 키를 이용하여 토큰으로부터 정보를 획득
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigninKey(accessSecret)).build().parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    // 토큰이 만료되었는지 확인
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    // 토큰 생성
    public JwtToken generateToken(UserDetails userDetails) {
        List<String> roles = new ArrayList<>();

        String uuid = UUID.randomUUID().toString();
        
        roles.add(uuid);
        return doGenerateToken(userDetails.getUsername(), roles);
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    // public String doGenerateToken(Map<String, Object> claims, String subject) {
    //     return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
    //         .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY_MINUTE * 60 * 1000))
    //         //.setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
    //         .signWith(getSigninKey(secret), SignatureAlgorithm.HS512).compact();
    //         //.signWith(SignatureAlgorithm.HS512, secret).compact();
    // }

    private Key getSigninKey(String secret) {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public JwtToken doGenerateToken(String userEmail, List<String> roles) {

        Claims claims = Jwts.claims().setSubject(userEmail); // JWT payload 에 저장되는 정보단위
        claims.put("roles", roles); // 정보는 key / value 쌍으로 저장된다.
        Date now = new Date();

        //Access Token
        String accessToken = Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + JWT_ACCESS_TOKEN_VALIDITY_TIME)) // set Expire Time
                .signWith(getSigninKey(accessSecret), SignatureAlgorithm.HS512)
                // .signWith(SignatureAlgorithm.HS256, accessSecret)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();

        //Refresh Token
        String refreshToken =  Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + JWT_REFRESH_TOKEN_VALIDITY_TIME)) // set Expire Time
                .signWith(getSigninKey(refreshSecret), SignatureAlgorithm.HS512)
                // .signWith(SignatureAlgorithm.HS256, refreshSecret)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();

        return JwtToken.builder().accessToken(accessToken).refreshToken(refreshToken).key(userEmail).build();
    }

    public String validateRefreshToken(JwtToken jwtToken){
        // refresh 객체에서 refreshToken 추출
        String refreshToken = jwtToken.getRefreshToken();

        try {
            // 검증
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(refreshSecret.getBytes()).build().parseClaimsJws(refreshToken);
            //refresh 토큰의 만료시간이 지나지 않았을 경우, 새로운 access 토큰을 생성합니다.
            if (!claims.getBody().getExpiration().before(new Date())) {
                return recreationAccessToken(claims.getBody().get("sub").toString(), claims.getBody().get("roles"));
            }
        }catch (Exception e) {
            //refresh 토큰이 만료되었을 경우, 로그인이 필요합니다.
            return null;
        }

        return null;
    }

    public String recreationAccessToken(String userEmail, Object roles){

        Claims claims = Jwts.claims().setSubject(userEmail); // JWT payload 에 저장되는 정보단위
        claims.put("roles", roles); // 정보는 key / value 쌍으로 저장된다.
        Date now = new Date();

        //Access Token
        String accessToken = Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + JWT_ACCESS_TOKEN_VALIDITY_TIME)) // set Expire Time
                .signWith(getSigninKey(accessSecret), SignatureAlgorithm.HS512)
                .compact();

        return accessToken;
    }
}
