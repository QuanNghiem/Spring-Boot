package com.mongodb.crud.app.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import com.mongodb.crud.app.model.User;
import com.mongodb.crud.app.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

    public static final long validity = 24 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    UserService serv;

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        System.out.println("User Id: " + user.get_id());
        return doGenerateToken(claims, user.get_id());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    // private String decodeToken(String token) {
    // Claims body =
    // Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    // return body.getId();
    // }

    // retrieve userID from jwt token
    public String getUserID(String token) {
        // return decodeToken(token);
        return getClaimFromToken(token, Claims::getSubject);
    }

    // Need this to work with authentication manager
    // Spring Boot is ... more complicated than NodeJS ...........
    public User getUser(String token) {
        String userId = getUserID(token);
        return serv.getUserById(userId).get();
    }

    // // validate token
    // public Boolean validateToken(HttpServletRequest request) {
    // final String requestTokenHeader = request.getHeader("Authorization");

    // String jwtToken = null;

    // // JWT Token is in the form "Bearer token".
    // // Remove Bearer word and get only the token.
    // if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
    // jwtToken = requestTokenHeader.substring(7);
    // final String userId = getUserID(jwtToken);
    // try {
    // // if token is valid configure Spring Security to manually set authentication
    // if (serv.verifyUser(userId) && !isTokenExpired(jwtToken)) {
    // return true;
    // }
    // } catch (IllegalArgumentException e) {
    // return false;
    // } catch (ExpiredJwtException e) {
    // return false;
    // }
    // }
    // return false;
    // }

    // validate token
    public Boolean validate(String token) {
        final String userId = getUserID(token);
        return (serv.verifyUser(userId) && !isTokenExpired(token));
    }
}
