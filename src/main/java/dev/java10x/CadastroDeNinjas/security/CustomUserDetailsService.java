package dev.java10x.CadastroDeNinjas.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        return User.builder()
                .username("user")
                .password("$2a$12$f1zJ0wN13vgH0xwXEbdS7.p6tyDGr1GCXLcboBKwJ0hHFJ178Dv/q")
                .build();
    }
}
