package com.Equipo.Futbol.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Equipo.Futbol.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtValidationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        String path = request.getRequestURI();
        return path.startsWith("/api/v1/football5/auth");

    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
            
        String autHeader = request.getHeader("Authorization");

        if (autHeader == null || !autHeader.startsWith("Bearer ")) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("Application/json");
        response.getWriter().write("{\"error\": \"Header is missing in the request\"}");
        return;
        }
        String token = autHeader.replace("Bearer ", "");

        try {
        if (jwtService.isTokenValid(token)){
        String username = jwtService.extractUsername(token);
        Long userId = jwtService.extractUserId(token);
        Long rolId = jwtService.extractRolId(token);

        request.setAttribute("username", username);
        request.setAttribute("userId", userId);
        request.setAttribute("rolId", rolId);

        filterChain.doFilter(request, response);

        }else {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("Application/json");
        response.getWriter().write("{\"error\": \"Token is Invalid or Expired\"}");
                            
             }
        } catch (Exception e) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("Application/json");
        response.getWriter().write("{\"error\": \"validation failed\"}");


        }
    }
}

    

