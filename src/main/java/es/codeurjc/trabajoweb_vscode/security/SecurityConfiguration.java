package es.codeurjc.trabajoweb_vscode.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import es.codeurjc.trabajoweb_vscode.security.jwt.JwtRequestFilter;
import es.codeurjc.trabajoweb_vscode.security.jwt.UnauthorizedHandlerJwt;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    public RepositoryUserDetailsService userDetailService;

    @Autowired
    private UnauthorizedHandlerJwt unauthorizedHandlerJwt;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**")
                .exceptionHandling(handling -> handling
                .authenticationEntryPoint(unauthorizedHandlerJwt)
                .accessDeniedHandler((request, response, ex) -> {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
                })
                )
                .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.POST, "/api/users/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/users/logout").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/books/").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/books/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/books/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/books/**").permitAll()


                .requestMatchers(HttpMethod.POST, "/api/authors/").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/authors/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/authors/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/authors/").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/authors/**").permitAll()


                .requestMatchers(HttpMethod.POST, "/api/reviews/").hasRole("USER")
                .requestMatchers(HttpMethod.PUT, "/api/reviews/**").hasRole("USER")
                .requestMatchers(HttpMethod.DELETE, "/api/reviews/**").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/reviews/**").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/users/search/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/books/search/**").permitAll()
                .requestMatchers("/api/**").authenticated()
                .anyRequest().denyAll()
                )
                .formLogin(formLogin -> formLogin.disable())
                .csrf(csrf -> csrf.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .sessionManagement(management -> management
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception {

        http.authenticationProvider(authenticationProvider());

        http
                .csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/**").permitAll()
                .requestMatchers("/adminLoggedIn/**").hasRole("ADMIN")
                .requestMatchers("/v3/api-docs*/**").permitAll()
                .requestMatchers("/swagger-ui.html").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .anyRequest().permitAll()
                )
                .formLogin(form -> form
                .loginPage("/login")
                .successHandler((request, response, authentication) -> {
                    String username = authentication.getName();
                    if ("admin".equals(username)) {
                        response.sendRedirect("/adminLoggedIn");
                    } else {
                        response.sendRedirect("/");
                    }
                })
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
