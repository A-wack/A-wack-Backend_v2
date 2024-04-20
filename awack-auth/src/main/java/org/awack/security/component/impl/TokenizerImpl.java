package org.awack.security.component.impl;

import io.jsonwebtoken.*;
import jakarta.websocket.OnError;
import lombok.RequiredArgsConstructor;
import org.awack.custom.JwtExpiredException;
import org.awack.custom.UserNotFoundException;
import org.awack.domain.user.model.UserEntity;
import org.awack.domain.user.port.FindUserPort;
import org.awack.security.component.Tokenizer;
import org.awack.security.config.SecurityProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TokenizerImpl implements Tokenizer {
    private final SecurityProperties properties;
    private final FindUserPort findUserPort;

    @Override
    public String create(String email) {
        Date now = new Date();

        return Jwts.builder()
                .setSubject(email)
                .signWith(SignatureAlgorithm.HS512, properties.getSecret())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + properties.getExpiration()))
                .setIssuer(properties.getIssuer())
                .setNotBefore(now)
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", properties.getAlgorithm())
                .compact();
    }

    @Override
    public boolean validate(String token) {
        return getClaims(token)
                .getIssuer()
                .equals(properties.getIssuer());
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(properties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public String getSubject(String token) {
        return getClaims(token)
                .getSubject();
    }

    @Override
    public String reissue(String token) {
        try {
            validate(token);
            return create(getSubject(token));
        } catch (ExpiredJwtException e) {
            String email = e.getClaims().getSubject();

            Date expiration = e.getClaims().getExpiration();

            Calendar cal = Calendar.getInstance();
            cal.setTime(expiration);
            cal.add(Calendar.HOUR_OF_DAY, 2);

            Date twoHoursLater = cal.getTime();

            Date now = new Date();
            if (now.before(twoHoursLater)) {
                return create(email);
            } else {
                throw JwtExpiredException.EXCEPTION;
            }
        } catch (JwtException e) {
            throw JwtExpiredException.EXCEPTION;
        }
    }

    @Override
    public Authentication getAuthentication(String token) {
        String subject = getSubject(token);
        UserEntity user = findUserPort.findByEmail(subject)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        UserDetails userDetails = new User(subject, "", List.of(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
        ));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    @Override
    public String extractToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return null;
    }

}
