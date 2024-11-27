package com.example.crop_monitoring_system.config;

import com.example.crop_monitoring_system.service.JWTService;
import com.example.crop_monitoring_system.service.UserService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Configuration
@RequiredArgsConstructor
public class JWTConfigFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        String userEmail;
        String extractedJWTToken;
        if (StringUtils.isEmpty(token) || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        extractedJWTToken = token.substring(7);
        userEmail = jwtService.extractUsername(extractedJWTToken);
        // user email
        if(StringUtils.isNotEmpty(userEmail) &&
                SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails =
                    userService.userDetailsService().loadUserByUsername(userEmail);
            if(jwtService.validateToken(extractedJWTToken, userDetails)) {
                //add user to the securoty context
                SecurityContext emptyContext =
                        SecurityContextHolder.createEmptyContext();
                var authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                emptyContext.setAuthentication(authToken);
                SecurityContextHolder.setContext(emptyContext);

            }
        }
        filterChain.doFilter(request, response);
    }
}
