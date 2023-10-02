package br.ufpb.dcx.projeto.dcs.logic.service;


import br.ufpb.dcx.projeto.dcs.db.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.sql.Date;
import java.util.Objects;

@Service
public class JWTService {

    public static final Key TOKEN_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(User user){
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(user.getEmail())
                .claim("name", user.getName())
                .claim("id", user.getId())
                .claim("role", user.getRole())
                .claim("userType", user.getType())
                .signWith(TOKEN_KEY, SignatureAlgorithm.HS512)
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) // 30 minutes
                .compact();
    }

    public String validateToken(String authorization){
        if(Objects.isNull(authorization) || !authorization.startsWith("Bearer ")){
            throw new SecurityException("Not authorized");
        }

        String token = authorization.substring("Bearer".length()).trim();

        try {
            JwtParser parser = Jwts.parserBuilder().setSigningKey(TOKEN_KEY).build();
            return parser.parseClaimsJws(token).getBody().getSubject();
        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            throw new SecurityException("Invalid or expired token");
        }
    }
}
