package dragonb.bearfamily.backend.configuration;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

// JWT 토큰 관련 설정 담당하는 클래스
@Component
public class JwtTokenUtil implements Serializable{
    private static final long serialVersionUID = -8522131160021027358L;
    public static final long JWT_TOKEN_VALIDITY_MINUTE = 10;
    
    @Value("${jwt.secret}")
    private String secret;

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
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    // 시크릿 키를 이용하여 토큰으로부터 정보를 획득
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigninKey(secret)).build().parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    // 토큰이 만료되었는지 확인
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    // 토큰 생성
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    public String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY_MINUTE * 60 * 1000))
            //.setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
            .signWith(getSigninKey(secret), SignatureAlgorithm.HS512).compact();
            //.signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    private Key getSigninKey(String secret) {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
