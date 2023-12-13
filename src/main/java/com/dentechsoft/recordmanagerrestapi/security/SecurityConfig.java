package com.dentechsoft.recordmanagerrestapi.security;

import com.dentechsoft.recordmanagerrestapi.service.ProsthetistService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    //bcrypt bean definition
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(){
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                boolean matches = super.matches(rawPassword, encodedPassword);
                System.out.println("Raw password: " + rawPassword);
                System.out.println("Encoded password: " + encodedPassword);
                System.out.println("Matches: " + matches);
                return matches;
            }
        };
    }

    //authenticationProvider bean definition
    @Bean
    public DaoAuthenticationProvider authenticationProvider(ProsthetistService prosthetistService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(prosthetistService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }

    // add support for JDBC
//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource){
//
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//        // define query to retrieve a user by username
//        jdbcUserDetailsManager.setUsersByUsernameQuery(
//                "select user_id, pw, active from prosthetist where user_id=?"
//        );
//
//        // define query to retrieve the authorities/roles by username
//        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
////                "select user_id, role from roles where user_id=?"
////                "select user_id, role_id from users_roles where user_id=?"
//                "SELECT ur.user_id, r.name " +
//                        "FROM users_roles ur " +
//                        "JOIN roles r ON ur.role_id = r.id " +
//                        "WHERE ur.user_id=?"
//        );
//
//        return  jdbcUserDetailsManager;
//    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception{


        httpSecurity.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/prosthetists").hasRole("PROSTHETIST")
                        .requestMatchers(HttpMethod.GET, "/api/prosthetists/**").hasRole("PROSTHETIST")
                        .requestMatchers(HttpMethod.POST, "/api/prosthetists").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/prosthetists").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/prosthetists/**").hasRole("OWNER")

                        .requestMatchers(HttpMethod.GET, "/api/patients").hasRole("PROSTHETIST")
                        .requestMatchers(HttpMethod.GET, "/api/patients/**").hasRole("PROSTHETIST")
                        .requestMatchers(HttpMethod.POST, "/api/patients").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/patients").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/patients/**").hasRole("OWNER")

                        .requestMatchers(HttpMethod.GET, "/api/dentists").hasRole("PROSTHETIST")
                        .requestMatchers(HttpMethod.GET, "/api/dentists/**").hasRole("PROSTHETIST")
                        .requestMatchers(HttpMethod.POST, "/api/dentists").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/dentists").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/dentists/**").hasRole("OWNER")
        );

        httpSecurity.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers(("/api/**")
                        ).permitAll()
        );

        // use HTTP Basic Authentication
        httpSecurity.httpBasic(Customizer.withDefaults());

        // disable Cross Site Request Forgery (CSRF)
        // in general not required for stateless REST APIs that use CRUD methods
        httpSecurity.csrf(csfr -> csfr.disable());



        return httpSecurity.build();
    }
}
