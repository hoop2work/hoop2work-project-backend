package com.hoop2work.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final RequestMappingHandlerMapping handlerMapping;

    public JwtAuthFilter(JwtService jwtService, RequestMappingHandlerMapping handlerMapping) {
        this.jwtService = jwtService;
        this.handlerMapping = handlerMapping;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        try {
            HandlerExecutionChain handlerChain = handlerMapping.getHandler(request);
            if (handlerChain != null && handlerChain.getHandler() instanceof HandlerMethod handlerMethod) {
                boolean requiresAuth = handlerMethod.hasMethodAnnotation(SecuredEndpoint.class);

                if (requiresAuth) {
                    String authHeader = request.getHeader("Authorization");
                    if (authHeader != null && authHeader.startsWith("Bearer ")) {
                        String token = authHeader.substring(7);
                        String username = jwtService.extractUsername(token);
                        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                            var auth = new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
                            SecurityContextHolder.getContext().setAuthentication(auth);
                        } else {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                            return;
                        }
                    } else {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing Authorization Header");
                        return;
                    }
                }
            }
        } catch (Exception e) {
            // Let Spring handle 404s and such
        }

        filterChain.doFilter(request, response);
    }
}
