package org.awack.security.config;

import lombok.RequiredArgsConstructor;
import org.awack.domain.user.model.UserRole;
import org.awack.security.component.Tokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final Tokenizer tokenizer;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/api/auth/login",
                                "/api/auth/register",
                                "/api/auth/reissue",
                                "/api/mail/send",
                                "/api/mail/confirm",
                                "/api/users/password",
                                "/api/auth/reissue").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/songs/admin").hasRole(UserRole.ADMIN.name())
                        .requestMatchers("/api/songs/current/{songId}").hasRole(UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.PATCH,"/api/songs/{id}").hasRole(UserRole.ADMIN.name())
                        .requestMatchers("/api/songs", "/api/songs/histories/me").hasRole(UserRole.STUDENT.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/songs/{songId}").hasRole(UserRole.STUDENT.name())
                        .anyRequest().authenticated())
                .addFilterBefore(new SecurityTokenFilter(tokenizer), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
