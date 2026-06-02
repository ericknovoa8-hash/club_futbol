package com.Equipo.Futbol.Security;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.Equipo.Futbol.enums.RoleEnum;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequiresRoleInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        if (!(handler instanceof HandlerMethod method)){
                return true;
        }
        RequiresRole annotation = method.getMethodAnnotation(RequiresRole.class);

        if (annotation == null) {
            annotation = method.getBeanType().getAnnotation(RequiresRole.class);
        }
        if  (annotation == null){
            return true;
        }
        Object rol = request.getAttribute("rolId");

        if  (!(rol instanceof Long rolId)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"usuario no autenticado\"}");
            return false;
        }

        boolean hasRole = Arrays.stream(annotation.value())
            .anyMatch(roleString -> {
                try {
                    RoleEnum role = RoleEnum.valueOf(roleString);
                    return role.getId().equals(rolId);
                } catch (IllegalArgumentException e) {
                    return false;
                }
            });

        if (!hasRole) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"No tiene permiso para realizar esta accion\"}");
            return false;
        }
        
        return true;
    }
}
