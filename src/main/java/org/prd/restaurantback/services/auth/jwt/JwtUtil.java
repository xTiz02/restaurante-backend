package org.prd.restaurantback.services.auth.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtUtil {
    //Authentication se usa para autenticar al usuario en el sistema y se crea luego de que el usuario se autentica en el sistema
    //UserDetails es una interfaz que se usa para obtener los datos del usuario autenticado en el sistema
    //Principal se usa para obtener el username del usuario autenticado en el sistema
    public static final String SECRET = "1232463562451341241847918346918365981349817398461938649136491834691834691836491836498136491836498136498136498134691834698163498163498134";
    public static final String TOKEN_PREFIX = "Bearer "; // prefijo del token en la cabecera de la petición
    public static final String HEADER_STRING = "Authorization";


    public String createToken(Authentication authentication) throws JsonProcessingException { //luego de autenticar al usuario, se crea el token
        String username = ((User) authentication.getPrincipal()).getUsername();
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        Claims claims = Jwts.claims();
        claims.put("authorities", new ObjectMapper().writeValueAsString(roles));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)//es el username del usuario autenticado en el sistema (email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // 1 dia
                .signWith(SignatureAlgorithm.HS256, getSigningKey()).compact();


    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }


    public Key getSigningKey() {//devuelve la clave secreta en bytes
        byte[] keyBytes = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String resolve(String token) {//esto hace que se quite el prefijo Bearer del token que se envia en la cabecera de la peticion para que se pueda validar el token en el servidor
        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            return token.replace(TOKEN_PREFIX, "");
        }
        return null;
    }
}

