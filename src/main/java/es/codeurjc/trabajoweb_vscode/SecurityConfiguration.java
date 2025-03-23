package es.codeurjc.trabajoweb_vscode;

import org.springframework.context.annotation.*;
import org.springframework.security.web.*;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    //TENGO QUE ESTUDIAR MÁS LA SEGURIDAD, PERO ESTO TIENE BUENA PINTA
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/**").permitAll()
                .requestMatchers("/private").hasAnyRole("USER")
                .requestMatchers("/admin").hasAnyRole("ADMIN")
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
            );

        return http.build();
    }



}