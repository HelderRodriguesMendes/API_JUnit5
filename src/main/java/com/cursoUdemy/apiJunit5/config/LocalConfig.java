package com.cursoUdemy.apiJunit5.config;

import com.cursoUdemy.apiJunit5.model.User;
import com.cursoUdemy.apiJunit5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public void startDB(){
        User u1 = new User("Cassio", "cassio@teste.com", "cassio123");
        User u2 = new User("Katia", "katia@teste.com", "katia123");
        User u3 = new User("Maria", "maria@teste.com", "maria123");
        User u4 = new User("Joaquim", "joaquim@teste.com", "joaquim123");
        userRepository.saveAll(List.of(u1, u2,u3, u4));
    }
}
