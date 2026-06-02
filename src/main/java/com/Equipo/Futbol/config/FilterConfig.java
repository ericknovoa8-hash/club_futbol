package com.Equipo.Futbol.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Equipo.Futbol.filter.JwtValidationFilter;

@Configuration
public class FilterConfig {
    @Bean
    FilterRegistrationBean<JwtValidationFilter> jwtFilter(JwtValidationFilter jwtValidationFilter) {
    
    // creamos un contenedor de registros del bean para el filtro
    FilterRegistrationBean<JwtValidationFilter> registrationBean = new FilterRegistrationBean<>();
    
    // le dice a spring que este es el filtro con el que quiero que trabaje
    registrationBean.setFilter(jwtValidationFilter);
    
    // Definir el alcance de este filtro, quiero que revise todas las peticiones que entren a mi aplicacion
    registrationBean.addUrlPatterns("/*");

    //Definimos el orden de prioridad de ejecucon de este bean
    registrationBean.setOrder(1);

    // Retornamos el orden de la prioridad de este bean 
    return registrationBean;

    }  
}
