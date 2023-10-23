package br.ufpb.dcx.projeto.dcs.filters;

import java.io.IOException;

import io.jsonwebtoken.security.SignatureException;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.PrematureJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static br.ufpb.dcx.projeto.dcs.logic.service.JWTService.TOKEN_KEY;

public class TokenFilterJWT extends GenericFilterBean {

    public final static int TOKEN_INDEX = 7;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        String header = req.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            sendErrorResponse((HttpServletResponse) response, "Authorization header missing or does not start with Bearer.");
            return;
        }

        String token = header.substring(TOKEN_INDEX);
        try {
            JwtParser parser = Jwts.parserBuilder().setSigningKey(TOKEN_KEY).build();
            parser.parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            sendErrorResponse((HttpServletResponse) response, "Token has expired.");
            return;
        } catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException | PrematureJwtException |
                 SignatureException e) {
            sendErrorResponse((HttpServletResponse) response, "Invalid token.");
            return;
        }

        chain.doFilter(request, response);
    }

    private void sendErrorResponse(HttpServletResponse response, String errorMessage) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + errorMessage + "\"}");
    }

}
