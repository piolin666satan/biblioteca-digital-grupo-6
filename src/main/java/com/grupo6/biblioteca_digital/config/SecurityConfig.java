package com.grupo6.biblioteca_digital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/docs/**",
                    "/actuator/**",
                    "/scalar.html"
                ).permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/clientes").permitAll()

                // CAMBIO 1: Catálogo de libros 100% público
                // Incluimos tanto la lista general como la búsqueda de un libro por su ID
                .requestMatchers(HttpMethod.GET, "/api/libros", "/api/libros/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/categorias", "/api/categorias/**").permitAll()

                // Las reglas de protección para el resto de la API se mantienen intactas
                .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("CLIENTE", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/api/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());
            
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // CAMBIO 2: Permitir peticiones desde CUALQUIER página web
        // Usamos setAllowedOriginPatterns en lugar de setAllowedOrigins
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        
        // Métodos permitidos
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        
        // Cabeceras permitidas (se permite todo con "*")
        configuration.setAllowedHeaders(Arrays.asList("*"));
        
        // Permitir envío de credenciales (útil al usar cookies o tokens en cabeceras específicas)
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Aplica la configuración de CORS a todas las rutas de la API
        source.registerCorsConfiguration("/**", configuration); 
        
        return source;
    }
}
