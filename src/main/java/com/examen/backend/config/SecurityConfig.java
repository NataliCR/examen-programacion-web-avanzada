    package com.examen.backend.config;

    import com.examen.backend.services.JwtService;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.http.HttpMethod;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
    public class SecurityConfig {

        private final JwtService jwtService;

        public SecurityConfig(JwtService jwtService) {
            this.jwtService = jwtService;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public JwtAuthenticationFilter jwtAuthenticationFilter() {
            return new JwtAuthenticationFilter(jwtService);
        }

        @Bean
        public SecurityFilterChain securityFilterChain(
                HttpSecurity http,
                JwtAuthenticationFilter jwtFilter
        ) throws Exception {

            http
                    .csrf(csrf -> csrf.disable())
                    .formLogin(form -> form.disable())
                    .httpBasic(basic -> basic.disable())
                    .sessionManagement(session -> session
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    )
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers(
                                    HttpMethod.POST,
                                    "/auth/registro",
                                    "/auth/login"
                            ).permitAll()
                            .anyRequest().authenticated()
                    )
                    .exceptionHandling(ex -> ex
                            .authenticationEntryPoint((request, response, error) ->
                                    response.sendError(
                                            401,
                                            "Se requiere un token JWT valido"
                                    )
                            )
                    )
                    .addFilterBefore(
                            jwtFilter,
                            UsernamePasswordAuthenticationFilter.class
                    );
            System.out.println("CONFIGURACION JWT CARGADA");
            return http.build();
        }
    }