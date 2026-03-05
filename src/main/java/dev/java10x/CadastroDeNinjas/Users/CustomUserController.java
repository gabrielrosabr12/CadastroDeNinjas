package dev.java10x.CadastroDeNinjas.Users;

import dev.java10x.CadastroDeNinjas.infra.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class CustomUserController {

    @Autowired
    CustomUserDetailsService customUserDetailsService;


}
