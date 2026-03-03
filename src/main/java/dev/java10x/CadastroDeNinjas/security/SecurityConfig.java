package dev.java10x.CadastroDeNinjas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Desabilita CSRF para o H2 e permite Frames (resolvendo o bloqueio do console)
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))

                // 2. Permissões de Rotas
                .authorizeHttpRequests(auth -> auth
                        // Libera explicitamente os estáticos e o console
                        .requestMatchers("/css/**", "/js/**", "/img/**", "/images/**", "/h2-console/**").permitAll()
                        // Se você tiver uma página de login ou cadastro pública, adicione aqui
                        .requestMatchers("/login", "/cadastro").permitAll()
                        // Todo o resto exige login
                        .anyRequest().authenticated()
                )

                // 3. Configura o Login (ajuste conforme seu controller)
                .formLogin(form -> form
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/css/**", "/js/**", "/img/**", "/favicon.ico");
    }
}
