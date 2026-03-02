package dev.java10x.CadastroDeNinjas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    /* UserDetailsService é o servico principal que recupera informações de seguranca sobre os usuários.
     * Como Definimos nosso bean personalizado, não vemos mais no log a senha padrão fornecida pelo
     * Spring, pois agora estamos fornecendo a nossa */
    @Bean
    public UserDetailsService userDetailsService() {

        /* UserDetails é a interface que o spring usa para processar informações de segurança do usuário
        * como nome,senha  (consulte a classe para obter mais informações) estamos usando a classe
        * User padrão como implementação*/
        UserDetails user = User.builder()
                .username("user")
                .password("$2a$12$wVbR6MbG6/lTW62HzagWHOgqCsLiCraap/M3PAGwdEDGyaGAYDesG")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
