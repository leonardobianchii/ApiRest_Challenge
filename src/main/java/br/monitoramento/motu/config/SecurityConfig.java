package br.monitoramento.motu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        var viewer = User.withUsername("viewer")
                .password(encoder.encode("viewer123"))
                .roles("MOTO_VIEW")
                .build();

        var editor = User.withUsername("editor")
                .password(encoder.encode("editor123"))
                .roles("MOTO_VIEW", "MOTO_EDIT")
                .build();

        return new InMemoryUserDetailsManager(viewer, editor);
    }

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                // estáticos e login liberados
                .requestMatchers("/", "/login", "/css/**", "/js/**", "/images/**").permitAll()

                // telas do thymeleaf - Motos
                .requestMatchers(HttpMethod.GET, "/motos/**").hasAnyRole("MOTO_VIEW", "MOTO_EDIT")
                .requestMatchers(HttpMethod.POST, "/motos/**").hasRole("MOTO_EDIT")
                .requestMatchers(HttpMethod.PUT, "/motos/**").hasRole("MOTO_EDIT")
                .requestMatchers(HttpMethod.DELETE, "/motos/**").hasRole("MOTO_EDIT")

                .anyRequest().authenticated()
        )
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/motos", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )

                .exceptionHandling(ex -> ex.accessDeniedPage("/403"))

                .csrf(Customizer.withDefaults());

        return http.build();
    }
}
