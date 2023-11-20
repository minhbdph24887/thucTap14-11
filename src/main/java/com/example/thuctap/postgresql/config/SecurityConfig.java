package com.example.thuctap.postgresql.config;

import com.example.thuctap.postgresql.entity.Account;
import com.example.thuctap.postgresql.service.AccountService;
import com.example.thuctap.postgresql.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.NoSuchElementException;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private String ADMIN = "admin";
    private String USER = "user";

    @Autowired
    AccountService accountService;

    @Autowired
    RoleService roleService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            try {
                Account account = accountService.detailAccountByUserNameAndPassword(username);
                String password = passwordEncoder().encode(account.getPassword());
                String roles = roleService.getAllRoleByUsernameAndPassword(username, account.getPassword()).getCodeRoles();
                return User.withUsername(username).password(password).roles(roles).build();
            } catch (NoSuchElementException e) {
                throw new UsernameNotFoundException(username + "Does not exist !");
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.disable()).csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/comment/**").hasRole(ADMIN)
                        .anyRequest().permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/logout").permitAll());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
