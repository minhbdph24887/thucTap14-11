package com.example.demo1;

import com.example.demo1.entity.Account;
import com.example.demo1.entity.Role;
import com.example.demo1.service.AccountService;
import com.example.demo1.service.AuthenticationService;
import com.example.demo1.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;

@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }

//    @Bean
//    BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    CommandLineRunner runRole(RoleService roleService){
//        return args -> {
//            roleService.saveRole(new Role(null, "ROLE_USER"));
//            roleService.saveRole(new Role(null, "ROLE_MANAGER"));
//            roleService.saveRole(new Role(null, "ROLE_ADMIN"));
//        };
//    }
//
//    @Bean
//    CommandLineRunner runAccount(AccountService accountService){
//        return args -> {
//            accountService.saveAccount(new Account(null, "account01", "Bui Duc Minh", "minhbdph24887@gmail.com", "123456", new HashSet<>()));
//            accountService.saveAccount(new Account(null, "account02", "Nguyen Van A", "anv12@gmail.com", "123456", new HashSet<>()));
//        };
//    }
//
//    @Bean
//    CommandLineRunner runAuthentication(AuthenticationService authenticationService){
//        return args -> {
//            authenticationService.addAuthentication("minhbdph24887@gmail.com", "ROLE_ADMIN");
//            authenticationService.addAuthentication("minhbdph24887@gmail.com", "ROLE_MANAGER");
//            authenticationService.addAuthentication("anv12@gmail.com", "ROLE_USER");
//        };
//    }
}
