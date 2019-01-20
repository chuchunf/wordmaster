package me.wordmaster.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class JWTService {
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
    private static final Logger LOGGER = Logger.getLogger(JWTService.class.getName());

    @Value("${app.name}")
    private String appname;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expires_timeout}")
    private int expires;

    public Optional<UserToken> getUserToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        if ( claims==null ) {
            return Optional.empty();
        }

        String username = claims.getSubject();
        Date issueAt = claims.getIssuedAt();

        return Optional.of(new UserToken(username, issueAt));
    }

    public String createToken(String username) {
        return Jwts.builder()
                .setIssuer(appname)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expires * 1000))
                .signWith(SIGNATURE_ALGORITHM, secret)
                .compact();
    }

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
            LOGGER.info("JWT parsing exception: " + e);
        }
        return claims;
    }
}
