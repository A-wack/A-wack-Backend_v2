package org.awack.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.awack.security.component.Tokenizer;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class SecurityTokenFilter extends OncePerRequestFilter {
    private final Tokenizer tokenizer;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        var token = tokenizer.extractToken(request.getHeader(HttpHeaders.AUTHORIZATION));

        if (token != null && tokenizer.validate(token)) {
            var authentication = tokenizer.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    
        filterChain.doFilter(request, response);
    }

}
