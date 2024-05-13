package org.awack.security.component.impl;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.awack.custom.JwtExpiredException;
import org.awack.custom.UserNotFoundException;
import org.awack.domain.user.port.FindUserPort;
import org.awack.security.component.Tokenizer;
import org.awack.security.config.SecurityProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TokenizerImpl implements Tokenizer {
    private final SecurityProperties properties;
    private final FindUserPort findUserPort;

    @Override
    public String create(String email) {
        var now = new Date();

        return Jwts.builder()
                .setSubject(email)
                .signWith(SignatureAlgorithm.HS512, properties.secret())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + properties.expiration()))
                .setIssuer(properties.issuer())
                .setNotBefore(now)
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", properties.algorithm())
                .compact();
    }

    @Override
    public boolean validate(String token) {
        return getClaims(token)
                .getIssuer()
                .equals(properties.issuer());
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(properties.secret())
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
            var email = e.getClaims().getSubject();

            var expiration = e.getClaims().getExpiration();

            var cal = Calendar.getInstance();
            cal.setTime(expiration);
            cal.add(Calendar.HOUR_OF_DAY, 2);

            var twoHoursLater = cal.getTime();

            var now = new Date();
            if (now.before(twoHoursLater)) {
                return create(email);
            } else {
                throw new JwtExpiredException();
            }
        } catch (JwtException e) {
            throw new JwtExpiredException();
        }
    }

    @Override
    public Authentication getAuthentication(String token) {
        var subject = getSubject(token);
        var user = findUserPort.findByEmail(subject)
                .orElseThrow(UserNotFoundException::new);

        var userDetails = new User(subject, "", List.of(
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
