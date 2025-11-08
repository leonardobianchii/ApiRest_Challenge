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
        // viewer: so consulta
        var viewer = User.withUsername("viewer")
                .password(encoder.encode("viewer123"))
                .roles("MOTO_VIEW")
                .build();

        // editor: consulta + altera
        var editor = User.withUsername("editor")
                .password(encoder.encode("editor123"))
                .roles("MOTO_VIEW", "MOTO_EDIT")
                .build();

        return new InMemoryUserDetailsManager(viewer, editor);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                // publico/estatico
                .requestMatchers("/", "/login", "/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()

                // ---------- listas (GET) liberadas para viewer e editor ----------
                .requestMatchers(HttpMethod.GET,
                        "/view/motos/**",
                        "/view/sensores/**",
                        "/view/localizacoes/**",
                        "/view/filiais/**")
                .hasAnyRole("MOTO_VIEW", "MOTO_EDIT")

                // ---------- telas de edicao (GET /new e /{id}/edit) so pra editor ----------
                .requestMatchers(HttpMethod.GET,
                        "/view/motos/new", "/view/motos/*/edit",
                        "/view/sensores/new", "/view/sensores/*/edit",
                        "/view/localizacoes/new", "/view/localizacoes/*/edit",
                        "/view/filiais/new", "/view/filiais/*/edit")
                .hasRole("MOTO_EDIT")

                // ---------- writes (POST/PUT/DELETE) so pra editor ----------
                .requestMatchers(HttpMethod.POST,
                        "/view/motos/**", "/view/sensores/**",
                        "/view/localizacoes/**", "/view/filiais/**")
                .hasRole("MOTO_EDIT")
                .requestMatchers(HttpMethod.PUT,
                        "/view/motos/**", "/view/sensores/**",
                        "/view/localizacoes/**", "/view/filiais/**")
                .hasRole("MOTO_EDIT")
                .requestMatchers(HttpMethod.DELETE,
                        "/view/motos/**", "/view/sensores/**",
                        "/view/localizacoes/**", "/view/filiais/**")
                .hasRole("MOTO_EDIT")

                // o resto precisa estar logado
                .anyRequest().authenticated()
        );

        // login basico com thymeleaf
        http.formLogin(login -> login
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login?error=true")
                .permitAll()
        );

        // logout simples
        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
        );

        // quando falta permissao
        http.exceptionHandling(ex -> ex.accessDeniedPage("/403"));

        // csrf no padrao
        http.csrf(Customizer.withDefaults());

        return http.build();
    }
}
