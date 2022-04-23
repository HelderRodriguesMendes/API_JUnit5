package com.cursoUdemy.apiJunit5.service;

import com.cursoUdemy.apiJunit5.model.User;
import com.cursoUdemy.apiJunit5.repository.UserRepository;
import com.cursoUdemy.apiJunit5.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado"));
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
